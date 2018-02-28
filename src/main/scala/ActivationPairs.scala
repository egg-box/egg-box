import breeze.generic.{MappingUFunc, UFunc}
import breeze.numerics._

object sigmoidDeriv extends UFunc with MappingUFunc {
  implicit object implDouble extends Impl[Double, Double] {
    def apply(a: Double) = sigmoid(a)*(1-sigmoid(a))
  }
}

object identity extends UFunc with MappingUFunc {
  implicit object implDouble extends Impl[Double, Double] {
    def apply(a: Double) = a
  }
}

object identityDeriv extends UFunc with MappingUFunc {
  implicit object implDouble extends Impl[Double, Double] {
    def apply(a: Double) = 1
  }
}

object ActivationPairs {
  def sigmoidPair = ActivationPair(sigmoid, sigmoidDeriv)
  def identityPair = ActivationPair(identity, identityDeriv)
}
