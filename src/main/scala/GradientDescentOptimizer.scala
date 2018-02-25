import breeze.linalg.DenseVector
import scala.collection.immutable.Vector

/*
class GradientDescentOptimizer extends Optimizer {
  def updateLayer(layers: Vector[DenseLayer], layerIndex: Int, previousError: DenseVector[Double],
                  yPredicted: DenseVector[Double], yTrue: DenseVector[Double]): DenseVector[Double] = {
    val theActFun = layers(layerIndex).actFunc
    def calculateCosts(): DenseVector[Double] = {
      yTrue-yPredicted.map(theActFun.activation)
    }
    def calculateLayerError(): Unit = {
      if (layerIndex == layers.length - 1) {
        calculateCosts()*yPredicted.map(theActFun.derivActivation)
      }
      else {
        
      }
    }
  }

  def updateLayers(layers: Vector[DenseLayer], yPredicted: DenseVector[Double]): Unit = {

  }
}
*/
