object traverse {
  def traverse_1[A, B](f: A => Option[B])(xs: List[A]): Option[List[B]] = {
    val ys = xs.map(f)
    if (ys.exists(_.isEmpty)) None
    else Some(ys.map(_.get))
  }


  def functionOpt (x:Int): Option[Int] = if (x  > 0) Some(x) else None

  val traverseCurry = traverse_1 (functionOpt)_

def sequence[A](xs: List[Option[A]]): Option[List[A]] =
    Some(xs map (_.getOrElse(return None)))

  def traverse_3[A, B](f: A => Option[B])(xs: List[A]): Option[List[B]] = {
    sequence(xs map f)
  }


}
