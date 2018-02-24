import breeze.generic.UFunc
import breeze.numerics.sigmoid
object ActivationPairs {
  object sigmoidDeriv extends UFunc {
    implicit object implDouble extends Impl[Double, Double] {
      def apply(a: Double) = (1 - sigmoid(a))*sigmoid(a)
    }
  }
  def sigmoidPair = ActivationPair(sigmoid, sigmoidDeriv)
}
