import Initializers._
import breeze.linalg.{DenseMatrix, DenseVector}

object Main {

  def main(args: Array[String]): Unit = {
    val n = new NeuralNet(4)
    n.add(DenseLayer(3, Activation.Relu, uniform, uniform))

    var inputData : DenseMatrix[Double] = DenseMatrix(DenseVector(-1.0, -2.0, -3.0, -4.0), DenseVector(5.0, 6.0, 7.0, 8.0))
    var outputData : DenseMatrix[Double] = DenseMatrix(0.0, 1.0, 2.0, 3.0)
    println(n.predict(inputData))
  }

}
