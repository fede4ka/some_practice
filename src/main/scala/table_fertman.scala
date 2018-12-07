object table_fertman extends App {

  println("Введите объём алкоголя, в мл")
  val v_start:Int = scala.io.StdIn.readLine().toInt
  println("Введите крепость алкоголя")
  val krep_start:Int = scala.io.StdIn.readLine().toInt
  println("Желаемая крепость алкоголя?")
  val krep_gel:Int = scala.io.StdIn.readLine().toInt

  val water:Int = ((100 * krep_start * (v_start/100))/krep_gel)-(100*v_start/100)
  println(s" Надо добавить $water млл воды")
}
