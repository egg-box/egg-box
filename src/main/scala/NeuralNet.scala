import breeze.linalg.{*, DenseMatrix, DenseVector}
import Activation.{Identity, act}

import scala.collection.mutable.ArrayBuffer
import Initializers.zeros

case class ForwardResult(a: Vector[DenseMatrix[Double]], z: Vector[DenseMatrix[Double]], f: DenseMatrix[Double])

class NeuralNet(numFeatures : Int) {
  private val _layers: ArrayBuffer[DenseLayer] = ArrayBuffer[DenseLayer]()
  private val _weights: ArrayBuffer[DenseMatrix[Double]] = ArrayBuffer[DenseMatrix[Double]]()
  private val _biases: ArrayBuffer[DenseVector[Double]] = ArrayBuffer[DenseVector[Double]]()

  // add input layer on instantiation
  _layers += DenseLayer(numFeatures, Identity, zeros, zeros)

  def add(newLayer: DenseLayer) : Unit = {
    _layers += newLayer
    val prevLayer = _layers(_layers.length - 2)

    // plus one to include bias node
    val baseWeights : DenseMatrix[Double] = newLayer.weightInitializer(prevLayer.numNeurons, newLayer.numNeurons)
    _weights += baseWeights
    val baseBiases : DenseMatrix[Double] = newLayer.biasInitializer(1, newLayer.numNeurons)
    _biases += baseBiases.toDenseVector
  }

  def forward(input: DenseMatrix[Double]): ForwardResult = {
    val z = ArrayBuffer[DenseMatrix[Double]]()
    var a = ArrayBuffer[DenseMatrix[Double]]()

    z += input
    a += input

    var current : DenseMatrix[Double] = input

    for (i <- _weights.indices) {
      // apply A_i
      current = current * _weights(i)
      current = current(*, ::) + _biases(i)
      // this becomes z_{i+1}
      z += current
      // apply f_{i+1}
      current = act(current, _layers(i+1).activation)
      // this becomes a_{i+1}
      a += current
    }

    ForwardResult(z.toVector, a.toVector, a(a.length - 1))
    // return all z, a, and the actual result which is a_{i+1}
  }

  def printNetwork(): Unit = {
    println("Layers: " + _layers)
    println("Weights: " + _weights)
  }

  /**
    * Method that will predict the outputs for the data based on the trained data
    * @param input The input data to feed through the NN
    * @return The predicted output of the input data
    */
  def predict(input: DenseMatrix[Double]): DenseMatrix[Double] = {
    forward(input).f
  }
}

