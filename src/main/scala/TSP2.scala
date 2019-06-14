object TSP2 extends App{
  //traveling salesman problem,greedy
  val testMap = Map (0 ->(1,1), 1->(1,2),2->(1,3),3->(2,3),4->(2,2),5->(2,1))
  val test2 = Map(0 -> (83,37), 1 -> (86,70), 2 -> (21,51), 3 -> (35,60), 4 -> (87,99))

  def EuclideanDistance(x:(Int,Int), y:(Int,Int)): Double =
    math.sqrt((x._1 - y._1)*(x._1 - y._1) + (x._2 - y._2)*(x._2 - y._2))

  def minRoute(dis:Map[(Int, Int),Double],curPoint:Int):((Int, Int), Double) =
    dis.filterKeys(x => x._1==curPoint).filter(x=>x._2!=0).minBy(_._2)

  def shortestRoute (map:Map[Int,(Int,Int)],startPoint:Int):(Vector[(Int, Int)], Double) = {
    def tr (map:Map[Int,(Int,Int)],curPoint:Int, pointsLeft: Set[Int], curRoute:Vector[(Int,Int)], curDistance:Double ):(Vector[(Int,Int)],Double) = {
      lazy val distances: Map[(Int, Int),Double] = (for(i <- 0 until map.size ;
                                                        j <- 0 until map.size) yield (i -> j) -> EuclideanDistance(map(i),map(j))).toMap
      if (pointsLeft.size>1)  {
        val (route,d) = minRoute(distances.filterKeys(x=>pointsLeft.contains(x._2)), curPoint)
        tr(map, route._2, pointsLeft - curPoint, curRoute :+ route, curDistance + d)
      }
      else (curRoute :+ (curPoint,startPoint) , curDistance + distances(curPoint,startPoint))
    }
    tr(map, startPoint, map.keys.toSet, Vector(),0.0)
  }
  println(shortestRoute(testMap, 0))
  //(Vector((0,5), (5,4), (4,1), (1,2), (2,3), (3,0)),7.23606797749979)
  println(shortestRoute(test2, 0))
  //(Vector((0,1), (1,4), (4,3), (3,2), (2,0)),207.35763061403165)
}
