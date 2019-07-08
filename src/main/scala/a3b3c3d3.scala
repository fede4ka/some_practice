object a3b3c3d3 extends App{
  //Write all solutions for a^3+b^3 = c^3 + d^3, where a, b, c, d lie between [0, 10^3].
  //найти все положительные целочисленные решения уравнения а3 + Ь3 = с3 + d3, меньшие 1000
  import scala.math.pow
  
  private val map = (for {a <- 1 to 1000
                  b <- a to 1000
                  cube = (pow(a,3)+pow(b,3)).toInt }
    yield Map (cube->(a,b))).fold(Map[Int,(Int, Int)]())((acc:Map[Int,(Int, Int)],y) => acc++y )

  private val result = for {c <- 1 to 1000
                 d <- c to 1000
                 cube = (pow(c,3)+pow(d,3)).toInt
                 if map.contains(cube)
                 if c!=d & c!=map(cube)._1 & c!=map(cube)._2
  } yield (c,d,map(cube)._1, map(cube)._2)

  print(result)
}
