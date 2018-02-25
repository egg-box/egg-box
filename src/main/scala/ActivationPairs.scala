import breeze.numerics.sigmoid

object ActivationPairs {
  def sigmoidPair = ActivationPair((x: Double) => sigmoid(x), (x: Double) => sigmoid(x) * (1 - sigmoid(x)))
  def identityPair = ActivationPair((x:Double) => x, (_:Double) => 1)
}
