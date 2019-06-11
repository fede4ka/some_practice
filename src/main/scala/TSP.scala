object TSP extends App {
  //traveling salesman problem,brute force
  val testMap = Map (0 ->(1,1), 1->(1,2),2->(1,3),3->(2,3),4->(2,2),5->(2,1))

  def distance(x:(Int,Int), y:(Int,Int)): Double =
    math.sqrt((x._1 - y._1)*(x._1 - y._1) + (x._2 - y._2)*(x._2 - y._2))

  def routesPermutations(points:Range) = points.permutations.filter(x => x(1) < x.reverse.head)
                                                              .filter(_(0) == points.head)//no repetitions

  def shortestRoot(map: Map[Int,(Int,Int)]): (IndexedSeq[(Int, Int)], Double) =  {
    lazy val distances = (for(i <- 0 until map.size ;
                              j <- 0 until map.size) yield (i -> j) -> distance(map(i),map(j))).toMap
    lazy val routes = routesPermutations(0 until map.size).map(x => x zip (x.tail :+ x.head)).toVector
    routes zip routes.par.map(x => x.foldLeft(0.0)((s,v) => s + distances(v)))minBy(_._2)
  }
print(shortestRoot(testMap))
 //(Vector((0,1), (1,2), (2,3), (3,4), (4,5), (5,0)),6.0)
}
