import breeze.linalg.DenseVector
import scala.util.Random

object Initializers {
  def constant = (v: Double) => (x: Int) => DenseVector.tabulate(x) { _ => v }
  def zeros = constant(0)
  def ones = constant(1)

  def uniform = (x: Int) => DenseVector.tabulate(x){_ => 1.0 / x}
  def randomUniform = (minVal: Double, maxVal: Double, seed: Integer) => {
    val rand = new Random(seed)
    (x: Int) => DenseVector.tabulate(x) { _ => rand.nextDouble() * (maxVal - minVal) + minVal }
  }
}
