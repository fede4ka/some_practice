object Exercise713 {

  def map1[A, B](xs: List[A]) (f: A => B): List[B] = {
    xs.foldRight(List.empty[B])((elem, acc) => f(elem) :: acc)
  }

  def flatmap1[A, B](xs: List[A])( f: A => List[B]): List[B] = {
    xs.foldRight(List.empty[B])((elem, acc) => f(elem) ::: acc)

  }
  def filter1[A](xs: List[A])(f: A => Boolean): List[A] = {
    xs.foldRight(List.empty[A])((elem, acc) =>
      if (f(elem)) elem::acc else acc)


  }


}