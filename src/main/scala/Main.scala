import Initializers._
import ActivationPairs._
import breeze.linalg.DenseMatrix

object Main {

  def main(args: Array[String]): Unit = {
    var n = new NeuralNet(5)
    n.add(DenseLayer(3, identityPair, ones, ones))
    n.add(DenseLayer(2, identityPair, ones, ones))
    println(n.forwardPropagation(DenseMatrix.tabulate(4, 5){(_, _) => 1})._3)
    n.printNetwork()
  }

}
