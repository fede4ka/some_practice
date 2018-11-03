object map {

  def map_1[A,B] (f: A=>B, seq: Seq[A]) : Seq[B] =
    for {x <- seq}
      yield f(x)

  def map_2[A,B](l: List[A])(f: A => B): List[B] = {
    val buf = new collection.mutable.ListBuffer[B]
    def go(l: List[A]): Unit = l match {
      case Nil => ()
      case h :: t => buf += f(h); go(t)
    }
    go(l)
    List(buf.toList: _*)
  }

  def map_3 [A,B](l: List[A])(f: A => B): List[B] =
    l.foldRight( Nil:List[B])((h,t) =>f(h)::t)

  def map_4[A,B](l: List[A])(f: A => B): List[B] =
    l.foldLeft( Nil:List[B])((h,t) =>f(t)::h).reverse

  def map_5(l: List[Int], f: Int => Int): List[Int] = {
    val buf = new collection.mutable.ListBuffer[Int]
    l foreach (buf += f(_))
    buf.toList
  }
}
