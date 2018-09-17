import breeze.linalg.{DenseMatrix, DenseVector}

import scala.util.Random

object Initializers {
  val rand = new Random()
  def constant(v: Double)(r: Int, c: Int): DenseMatrix[Double] = {
    DenseMatrix.tabulate(r, c) { (_,_) => v }
  }
  def zeros: (Int, Int) => DenseMatrix[Double] = constant(0)(_,_)
  def ones: (Int, Int) => DenseMatrix[Double] = constant(1)(_,_)
  //def uniform = (x: Int) => DenseVector.tabulate(x){_ => 1.0 / x}
  def uniform(r: Int, c: Int): DenseMatrix[Double] = {
    DenseMatrix.tabulate(r, c) { (_,_) => rand.nextDouble() }
  }
}
