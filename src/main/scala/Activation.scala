import breeze.linalg.DenseMatrix
import breeze.numerics
object Activation extends Enumeration {
  type Activation = Value
  val Identity, Sigmoid, Relu = Value

  def act(m: DenseMatrix[Double], a: Activation.Value): DenseMatrix[Double] = {
    a match {
      case Activation.Identity => m
      case Activation.Sigmoid => numerics.sigmoid(m)
      case Activation.Relu => numerics.relu(m)
    }
  }
}