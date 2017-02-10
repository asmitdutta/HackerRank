/**
  * Created by asmit on 14/5/16.
  */
object SwapNodes {

  case class Node(value:Int,left:Option[Node],right:Option[Node])

  def formTree(node:Option[Node],arr:List[Node]):Option[Node] = {
    node match {
      case None => None
      case Some(node) => Some(Node(node.value,
          formTree(arr(node.value).left,arr),
          formTree(arr(node.value).right,arr))
        )
    }
  }

  def height(node:Option[Node]):Int = {
    node match {
      case None => 0
      case Some(node) => 1 + Math.max(height(node.left),height(node.right))
    }
  }

  def swapNode(node:Option[Node],k:Int,d:Int):Option[Node] = {
    node match {
      case None => None
      case Some(node) =>
        if(d==k) Some(Node(node.value,node.right,node.left))
        else Some(Node(node.value,swapNode(node.left,k,d+1),swapNode(node.right,k,d+1)))
    }
  }

  def inorder(node:Option[Node]):Unit = {
    node match {
      case None => ()
      case Some(node) =>
        inorder(node.left)
        print(node.value + " ")
        inorder(node.right)
    }
  }

  def main(args:Array[String]) = {
    val N = Console.readInt
    val arr:Array[Node] = Array.fill(N+1)(Node(-1,None,None))
    (1 to N) foreach { i =>
      val line = Console.readLine()
      val leftValue = line.split(" ")(0).toInt
      val rightValue = line.split(" ")(1).toInt
      arr(i) = Node(i,
        if(leftValue == -1)None else Some(Node(leftValue,None,None)),
        if(rightValue == -1)None else Some(Node(rightValue,None,None))
      )
    }
    val tree = Node(1, formTree(arr(1).left,arr.toList), formTree(arr(1).right,arr.toList))
    val T = Console.readInt()
    val h = height(Some(tree))
    (1 to T).foldLeft(Option(tree)){(a:Option[Node],i:Int) =>
      val K = Console.readInt()
      val swappedTree = (1 to h/K).foldLeft(a){(m:Option[Node],i:Int) => swapNode(m,i*K,1)}
      inorder(swappedTree)
      println()
      swappedTree
    }
    ()
  }

}
