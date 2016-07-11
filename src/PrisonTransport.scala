/**
  * Created by asmit on 24/5/16.
  */
import Array._

object PrisonTransport {

  var status:Array[Int] = _
  var N:Int = _

  def formGroup(m:Array[Array[Int]],p:Int,N:Int):List[Int] = {
    if(status(p)==0){
      status(p) = 1
      val group = (1 to N).flatMap{ v =>
        val list = if (m(p)(v) == 1 && status(v)!=1) {
          println(p+":"+v+":"+status(v))
          p :: formGroup(m,v,N)
        }else List.empty[Int]
        list
      }
      status(p) = 2
      group.toList
    }else Nil
  }

  def formGroups(m:Array[Array[Int]],N:Int):List[List[Int]] = {
    (1 to N).map{p => formGroup(m,p,N)}.toList
  }

  def main(args:Array[String]) = {
    N = Console.readInt()
    status = Array.fill[Int](N+1)(0)
    val M = Console.readInt()
    val adjacencyMatrix = ofDim[Int](N+1,N+1)
    (1 to M) foreach {i =>
      val pair = Console.readLine().split(" ").map(_.toInt).toList
      val P = pair(0)
      val Q = pair(1)
      adjacencyMatrix(P)(Q) = 1
      adjacencyMatrix(Q)(P) = 1
    }
    val groupList:List[List[Int]] = formGroups(adjacencyMatrix,N)
    println(groupList)
  }

}
