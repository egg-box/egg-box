import breeze.linalg.DenseVector
import ActivationPairs.identityPair


object Main {

  def main(args: Array[String]): Unit = {
    var n = new NeuralNet(3)
    n.add(DenseLayer(2, identityPair))
    n.add(DenseLayer(4, identityPair))
    println(n.forwardPropagation(DenseVector.tabulate(3){x : Int => x * 2}))
  }

}
