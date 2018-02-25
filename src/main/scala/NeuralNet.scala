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
    _weights = _weights :+ DenseMatrix.tabulate(newLayer.numNeurons, prevLayer.numNeurons + 1) {
      (i, j) => {
        baseWeights(j)
      }
    }
  }

  // should be private
  def forwardPropagation(input: DenseVector[Double]): (Vector[DenseVector[Double]], Vector[DenseVector[Double]], DenseVector[Double]) = {
    var z = Vector[DenseVector[Double]]()
    var a = Vector[DenseVector[Double]]()

    z = z :+ input
    a = a :+ input

    var current : DenseVector[Double] = input

    for (i <- _weights.indices) {
      println(i + " " + current.length + " " + _weights(i).rows + "x" + _weights(i).cols)
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
  }

  def printNetwork(): Unit = {
    println("Layers: " + _layers)
    println("Weights: " + _weights)
  }


}
