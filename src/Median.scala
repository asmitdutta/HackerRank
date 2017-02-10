

object Median{

  case class Node(median:Int,list:Array[Int])

  var medianList = Array[Node]()
  var step = 0

  def getMedian(list:Array[Int]):Int = {
    val n = if(list.size%2==0) list.size/2 else (list.size + 1)/2
    list(n-1)
  }

  def findMedian(i:Int):Int = {
    step = step + 1
    if(i >= 0){
      val list:Array[Int] = if(!medianList.isEmpty) (medianList.last.list :+ i).sorted else Array(i)
      val median:Int = getMedian(list)
      medianList = medianList :+ Node(median,list)
      medianList.last.median
    }else{
      val state = step + i
      val median = medianList(state-1).median
      val list = medianList(state-1).list
      medianList = medianList :+ Node(median,list)
      median
    }
  }

  def main(args:Array[String]): Unit ={
    val T = Console.readInt()
    (1 to T).map{i =>
      val n = Console.readInt()
      println(findMedian(n))
    }
  }
}