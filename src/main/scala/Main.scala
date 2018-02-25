import WeightInitializers._
import ActivationPairs._

object Main {

  def main(args: Array[String]): Unit = {
    var n = new NeuralNet(5)
    n.add(DenseLayer(3, identityPair, ones))
    n.add(DenseLayer(2, sigmoidPair, uniform))
    n.printNetwork()
  }

}
