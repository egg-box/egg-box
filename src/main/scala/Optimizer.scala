import breeze.linalg.{DenseMatrix, DenseVector}

import scala.collection.immutable.Vector

abstract class Optimizer {
  def updateLayers(layers: Vector[DenseLayer], yPredicted: DenseVector[Double],
                   yTrue: DenseMatrix[Double], a: Vector[DenseMatrix[Double]],
                   z: Vector[DenseMatrix[Double]], weights: Vector[DenseMatrix[Double]],
                   learningRate: Double): Vector[DenseMatrix[Double]]
}