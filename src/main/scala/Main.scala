import Initializers._
import ActivationPairs._
import breeze.linalg.DenseMatrix

object Main {

  def main(args: Array[String]): Unit = {
    var n = new NeuralNet(1)
    n.add(DenseLayer(1, identityPair, constant(0.5), constant(0.5)))

    var inputData : DenseMatrix[Double] = DenseMatrix(1.0, 2.0, 3.0, 4.0)
    var outputData : DenseMatrix[Double] = DenseMatrix(0.0, 1.0, 2.0, 3.0)

    for (i <- 0 until 100) {
      n.train(inputData, outputData)
    }
  }

}
