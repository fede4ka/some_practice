object Option extends App{
  sealed trait Opt[+A]
  {
    def isDefined : Boolean
    def get() : A
    def map[B](f: A => B): Opt[B]
    def flatMap[B](f: A => Opt[B]): Opt[B]
    def getOrElse[B >: A](default: => B): B = if (isDefined) get() else default
    def orElse[B >: A](ob: => Opt[B]): Opt[B]
    def filter(f: A => Boolean): Opt[A]
  }

  final case class Som[A] (elem:A) extends  Opt[A]
  {
    override def isDefined: Boolean = true
    override def get():A = elem
    override def map [B](f: A => B): Opt[B] = Som(f(elem))
    override def flatMap[B](f: A => Opt[B]): Opt[B] = f(elem)
    override def orElse[B >: A](ob: => Opt[B]): Opt[B] = if (isDefined) Som(elem) else ob
    override def filter(f: A => Boolean): Opt[A] = if (f(elem)) Som(elem) else Non
  }


  final case object Non extends Opt[Nothing]
  {
    override def isDefined: Boolean = false
    override def get: Nothing = throw new NoSuchElementException("This is a None")
    override def map[B](f: Nothing => B): Opt[B] = Non
    override def flatMap[B](f: Nothing => Opt[B]): Opt[B] = Non
    override def orElse[B >: Nothing](ob: => Opt[B]): Opt[B] = ob
    override def filter(f: Nothing => Boolean): Opt[Nothing] = Non
  }

  println(Som(2).map(_+2).get())
}
