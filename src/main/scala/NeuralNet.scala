import ActivationPairs.identityPair
import breeze.linalg.{DenseMatrix, DenseVector}

import scala.collection.immutable.Vector
import Initializers.zeros


class NeuralNet(numFeatures : Int) {
  private var _layers: Vector[DenseLayer] = Vector[DenseLayer]()
  private var _weights: Vector[DenseMatrix[Double]] = Vector[DenseMatrix[Double]]()

  // add input layer on instantiation
  _layers = _layers :+ DenseLayer(numFeatures, identityPair, zeros, zeros)

  def add(newLayer: DenseLayer) : Unit = {
    _layers = _layers :+ newLayer
    val prevLayer = _layers(_layers.length - 2)

    // plus one to include bias node
    val baseWeights : DenseVector[Double] = DenseVector.vertcat(newLayer.weightInitializer(prevLayer.numNeurons), newLayer.biasInitializer(1))
    _weights = _weights :+ DenseMatrix.tabulate(prevLayer.numNeurons + 1, newLayer.numNeurons) {
      (i, j) => {
        baseWeights(i)
      }
    }
  }

  /*// should be private
  def forwardPropagation(input: DenseVector[Double]): (Vector[DenseVector[Double]], Vector[DenseVector[Double]], DenseVector[Double]) = {
    var z = Vector[DenseVector[Double]]()
    var a = Vector[DenseVector[Double]]()

    z = z :+ input
    a = a :+ input

    var current : DenseVector[Double] = input

    for (i <- _weights.indices) {
      // apply A_i
      current = DenseVector.vertcat(current, DenseVector.ones(1))
      current =  _weights(i) * current
      // this becomes z_{i+1}
      z = z :+ current
      // apply f_{i+1}
      current = current.map(_layers(i+1).actFunc.activation)
      // this becomes a_{i+1}
      a = a :+ current
    }

    (z, a, a(a.length - 1))
    // return all z, a, and the actual result which is a_{i+1}
  }*/

  def forwardPropagation(input: DenseMatrix[Double]): (Vector[DenseMatrix[Double]], Vector[DenseMatrix[Double]], DenseMatrix[Double]) = {
    var z = Vector[DenseMatrix[Double]]()
    var a = Vector[DenseMatrix[Double]]()

    z = z :+ input
    a = a :+ input

    var current : DenseMatrix[Double] = input

    for (i <- _weights.indices) {
      // apply A_i
      current = DenseMatrix.horzcat(current, DenseMatrix.tabulate(current.rows, 1){case (_, _) => 1.0})
      current = current * _weights(i)
      // this becomes z_{i+1}
      z = z :+ current
      // apply f_{i+1}
      current = current.map(_layers(i+1).actFunc.activation)
      // this becomes a_{i+1}
      a = a :+ current
    }

    (z, a, a(a.length - 1))
    // return all z, a, and the actual result which is a_{i+1}
  }

  def printNetwork(): Unit = {
    println("Layers: " + _layers)
    println("Weights: " + _weights)
  }

  /**
    * Method that will tune the weights of the neurons of the graph
    * @param trainingData The data passed in by the user to train the neural net
    */
  def train(trainingData: DenseMatrix[Double], yTrue: DenseMatrix[Double]): Unit = {
    val propagationData = forwardPropagation(trainingData)

    val optimizer = new GradientDescentOptimizer()
    println(propagationData._1)
    _weights = optimizer.updateLayers(_layers, yTrue, propagationData._2, propagationData._1, _weights, 0.01)

    println("Finished training!")
  }

  /**
    * Method that will predict the outputs for the data based on the trained data
    * @param input The input data to feed through the NN
    * @return The predicted output of the input data
    */
  def predict(input: DenseMatrix[Double]): DenseMatrix[Double] = {
    return forwardPropagation(input)._3
  }
}
