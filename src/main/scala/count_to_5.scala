import akka.actor.{ ActorSystem, Props, Actor }
class actor1 extends Actor {
  var ponged = false
  val child = context.actorOf(Props[actor2])
  def receive = {
    case "start" => child ! 0
    case c:Int   => if (c<5) child ! c else println(c)
  }
}
class actor2 extends Actor {
  def receive = {
    case count:Int => sender() ! count+1
      println(count)
  }
}

object app extends App {
val system = ActorSystem("Ping")
val actor = system.actorOf(Props[actor1],"actor1")

actor ! "start"

  system.terminate()
}
