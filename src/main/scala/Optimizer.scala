import scala.collection.immutable.Vector

abstract class Optimizer {
  def updateLayers(layers: Vector[Layer]): Unit
}