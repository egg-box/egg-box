import breeze.linalg.DenseMatrix
case class DenseLayer(numNeurons: Int, activation: Activation.Value,
                      weightInitializer: (Int, Int) => DenseMatrix[Double],
                      biasInitializer: (Int, Int) => DenseMatrix[Double]) extends Layer