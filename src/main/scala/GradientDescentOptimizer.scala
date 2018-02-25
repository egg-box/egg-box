import breeze.linalg.{*, DenseMatrix, DenseVector, sum}

import scala.collection.immutable.Vector


class GradientDescentOptimizer extends Optimizer {
  def calculateLayerErrors(layers: Vector[DenseLayer],
                  yTrue: DenseMatrix[Double], a: Vector[DenseMatrix[Double]],
                  z: Vector[DenseMatrix[Double]],
                  weights: Vector[DenseMatrix[Double]]): Vector[DenseMatrix[Double]] = {

    def calculateCosts(): DenseMatrix[Double] = {
      a(a.length - 1)-yTrue//shouldnt include actfun I think .map(theActFun.activation) //Check this
    }

    def calculateLayerError(layerIndex: Int, previousError: DenseMatrix[Double]): DenseMatrix[Double] = {
      val theActFun = layers(layerIndex).actFunc
      if (layerIndex == layers.length - 1) {
        print(z.)
        calculateCosts() *:* z(layerIndex).map(theActFun.derivActivation)
      }
      else {
        println(previousError.rows, previousError.cols)
        weights(layerIndex).t * previousError *:* z(layerIndex).map(theActFun.derivActivation)//Index correct?
      }
    }

    var dm = DenseMatrix.zeros[Double](0,0)
    var vec = Vector[DenseMatrix[Double]]()
    for(layerIndex <- layers.length -1 to 0 by -1){
      dm = calculateLayerError(layerIndex, dm)
      vec = vec:+dm
    }
    vec
  }

  def updateLayers(layers: Vector[DenseLayer],
                  yTrue: DenseMatrix[Double], a: Vector[DenseMatrix[Double]],
                  z: Vector[DenseMatrix[Double]], weights: Vector[DenseMatrix[Double]],
                   learningRate: Double): Vector[DenseMatrix[Double]] = {
    val errors = calculateLayerErrors(layers, yTrue, a, z, weights)
    for (layerIndex <- layers.length-1 to 1 by -1){
      val inner = errors(layerIndex)*a(layerIndex-1).t
      weights(layerIndex) := weights(layerIndex) - learningRate/yTrue.rows*sum(inner(::, *))
    }
    weights
  }
}

