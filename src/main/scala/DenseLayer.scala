import breeze.linalg.DenseVector

case class DenseLayer(numNeurons: Int, actFunc: ActivationPair, weightInitializer: Int => DenseVector[Double]) extends Layer