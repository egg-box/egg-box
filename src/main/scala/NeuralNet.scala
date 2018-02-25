import ActivationPairs.identityPair
import breeze.linalg.{DenseMatrix, DenseVector}

import scala.collection.immutable.Vector
import WeightInitializers.ones


class NeuralNet(numFeatures : Int) {
  private var _layers: Vector[DenseLayer] = Vector[DenseLayer]()
  private var _weights: Vector[DenseMatrix[Double]] = Vector[DenseMatrix[Double]]()

  // add input layer on instantiation
  _layers = _layers :+ DenseLayer(numFeatures, identityPair, ones)

  def add(newLayer: DenseLayer) : Unit = {
    _layers = _layers :+ newLayer
    val prevLayer = _layers(_layers.length - 2)

    // plus one to include bias node
    val baseWeights : DenseVector[Double] = DenseVector.vertcat(newLayer.weightInitializer(prevLayer.numNeurons), DenseVector.ones(1))
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
  def train(trainingData: DenseMatrix[Double]): Unit = {

    // val optimizer = new GradientDescentOptimizer()

    val propogationData = forwardPropagation(trainingData)

    // TODO:: Uncomment this when the optomizer is finished
    // optimizer.updateLayers(_weights, propogationData._1, propogationData._2, propogationData._3)

    println("Finished training!")
  }

  def predict(predictingData: DenseMatrix[Double]): DenseMatrix[Double] = {

  }
}
