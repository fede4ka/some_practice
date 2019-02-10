object maximum_subarray extends  App {
  val arr = Array(1,2,3,-6,5,15,-30, 20,-1,15)

  def maxArraySublist (xs: Array[Int]): Int = {
    var maxsum = 0
    var sum = 0
    for (i<- xs.indices) {
      sum+= xs(i)
      if (sum>maxsum) maxsum = sum
      if (sum<0) sum = 0
    }
    maxsum
  }
  print(maxArraySublist(arr))
}
