import breeze.linalg.DenseVector
import scala.collection.immutable.Vector

class GradientDescentOptimizer extends Optimizer {
  def updateLayer(layers: Vector[Layer], layerIndex: Int,
                  previousError: DenseVector[Double]): DenseVector[Double] = {
    def calculateLayerError(): Unit = {
      if (layerIndex == layers.length - 1) {
        
      }
      else {

      }
    }
  }

  def updateLayers(layers: Vector[Layer]): Unit = {

  }
}
