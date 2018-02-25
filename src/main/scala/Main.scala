import WeightInitializers._
import ActivationPairs._
import breeze.linalg.{DenseMatrix, DenseVector}

object Main {

  def main(args: Array[String]): Unit = {
    var n = new NeuralNet(5)
    n.add(DenseLayer(3, identityPair, ones))
    n.add(DenseLayer(2, identityPair, ones))
    println(n.forwardPropagation(DenseVector.ones(5))._1)
    println(n.forwardPropagationMatrix(DenseMatrix.tabulate(5, 2){(_, _) => 1})._3)
  }

}
