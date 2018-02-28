import breeze.generic.{MappingUFunc, UFunc}
//https://github.com/scalanlp/breeze/issues/159
case class ActivationPair[Tag1 <: UFunc with MappingUFunc, Tag2 <: UFunc with MappingUFunc](actFun: Tag1, derFun: Tag2) {
  def activation[T](x: T)(implicit aFun: UFunc.UImpl[Tag1, T, T]): T = {
    aFun(x)
  }
  def activationDeriv[T](x: T)(implicit dFun: UFunc.UImpl[Tag2, T, T]): T = {
    dFun(x)
  }
}