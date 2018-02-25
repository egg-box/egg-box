import breeze.generic.{MappingUFunc, UFunc}
import breeze.linalg.DenseVector

object TestUFunc {
  def main(args: Array[String]): Unit = {
    val dv: DenseVector[Double] = DenseVector[Double](1.0, 2.0, 3.0)
    println(TheUFunc.log._1(dv))
    println(TheUFunc.log._1.getClass())
  }
}
