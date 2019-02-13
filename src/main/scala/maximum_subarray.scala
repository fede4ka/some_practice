object maximum_subarray extends  App {
  val arr = Array(1,5,-7,2,3,15,-6,5,150,-30, 20,-144,15,11,-200)

  def maxArraySublist (xs: Array[Int]): List[Int] = {
    var maxsum,sum,start,end,curstart = 0

    for (i<- xs.indices) {
      sum+= xs(i)
      if (sum>maxsum) {maxsum = sum; end = i;start=curstart}
      if (sum<0) {sum = 0;curstart=i+1}
    }
    List(maxsum,start,end)
  }
  print(maxArraySublist(arr))
}
