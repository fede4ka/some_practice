object MazeSolver extends App {
  def solver(maze: Array[Array[Int]]): Array[Array[Int]] = {
    val finish = (maze.length-1,maze(1).length-2)
    def tr (maze: Array[Array[Int]],cp:(Int,Int)): Array[Array[Int]] = {
      if ((cp._1==finish._1)&&(cp._2==finish._2)) {maze}
      else if (maze(cp._1+1)(cp._2)==0) {
        maze(cp._1)(cp._2) = 2
        tr(maze,(cp._1+1,cp._2))
      }
      else if (maze(cp._1-1)(cp._2)==0) {
        maze(cp._1)(cp._2) = 2
        tr(maze,(cp._1-1,cp._2))
      }

      else if (maze(cp._1)(cp._2+1)==0) {
        maze(cp._1)(cp._2) = 2
        tr(maze,(cp._1,cp._2+1))
      }
      else if (maze(cp._1)(cp._2-1)==0) {
        maze(cp._1)(cp._2) = 2
        tr(maze,(cp._1,cp._2-1))
      }
      //dead end
      else if (maze(cp._1+1)(cp._2)==2) {
        maze(cp._1)(cp._2) = 3
        tr(maze,(cp._1+1,cp._2))
      }
      else if (maze(cp._1-1)(cp._2)==2) {
        maze(cp._1)(cp._2) = 3
        tr(maze,(cp._1-1,cp._2))
      }
      else if (maze(cp._1)(cp._2+1)==2) {
        maze(cp._1)(cp._2) = 3
        tr(maze,(cp._1,cp._2+1))
      }
      else  {
        maze(cp._1)(cp._2) = 3
        tr(maze,(cp._1,cp._2-1))
      }
    }
    tr(maze,(1,1))
  }
  def generate(width: Int, height: Int): Array[Array[Int]] = {
    val w = if (width %2 !=0) width else width+1
    val h = if (height %2 !=0) height else height+1
    val maze = Array.fill[Int](h, w)(1)
    maze(1)(1) = 0
    carve(1, 1)
    maze(h - 1)(w - 2) = 0

    def carve(x: Int, y: Int) {
      def update_pos(dir: Int, x: Int, y: Int): (Int, Int) = dir match {
        case 0 => (x + 1, y + 0)
        case 1 => (x + 0, y + 1)
        case 2 => (x - 1, y + 0)
        case _ => (x + 0, y - 1)
      }
      var dir = scala.util.Random.nextInt(4)
      var count:  Int = 0
      while(count < 4) {
        val (x1, y1) = update_pos(dir, x, y)
        val (x2, y2) = update_pos(dir, x1, y1)
        if(x2 > 0 && x2 < w && y2 > 0 && y2 < h) {
          if(maze(y1)(x1) == 1 && maze(y2)(x2) == 1) {
            maze(y1)(x1) = 0
            maze(y2)(x2) = 0
            carve(x2, y2)
          }
        }
        count += 1
        dir = (dir + 1) % 4
      }
    }
    maze
  }

  def show (maze: Array[Array[Int]]) {
    maze.foreach(row =>
    {
      row.foreach(block => print(if(block == 1) "[]"
      else if(block==2) " x"
      else "  "))
      println
    }
    )
  }
show(solver(generate(32,32)))

}
