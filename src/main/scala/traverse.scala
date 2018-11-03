object traverse {
  def traverse_1[A, B](f: A => Option[B])(xs: List[A]): Option[List[B]] = {
    val ys = xs.map(f)
    if (ys.exists(_.isEmpty)) None
    else Some(ys.map(_.get))
  }


  def functionOpt (x:Int): Option[Int] = if (x  > 0) Some(x) else None


  val traverseCurry = traverse_1 (functionOpt)_




}
