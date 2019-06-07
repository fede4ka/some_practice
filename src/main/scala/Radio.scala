object Radio extends App{
  //Greedy algorithm, Set cover problem
  val states = Map ("k1" ->  Set ("id", "nv", "ut"),
    "k2"->  Set ("wa", "id", "mt"),
    "k3"->  Set ("or", "nv", "ca"),
    "k4"->  Set ("nv", "ut"),
    "k5"->  Set ("ca", "az")
  )
  val states_needed = Set( "mt", "wa", "or", "id", "nv", "ut","ca","az" )

  def findMaxIntersect (m:Map[String,Set[String]], list:Set[String]):(String,Set[String]) = {
    val r =m.mapValues(_.intersect(list)).mapValues(_.size).maxBy(_._2)
    (r._1, m(r._1))
  }
  def minRadio (m:Map[String,Set[String]], states:Set[String]):Set[String] = {
    def minRadiotr(radio:Map[String,Set[String]], states:Set[String],result:Set[String]):Set[String] = {
      if (states.nonEmpty)
      {
        val a = findMaxIntersect(radio,states)
        println(states)
        println(result)
        minRadiotr(radio,states--a._2, result+a._1)
      }
      else result
    }
    minRadiotr(m,states,Set())
  }
  println(minRadio(states,states_needed))
}