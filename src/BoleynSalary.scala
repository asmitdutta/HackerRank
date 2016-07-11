import scala.collection.mutable

/**
  * Created by asmit on 15/4/16.
  */

object BoleynSalary {

    case class Node(var subordinateList:List[Node], var subordinateSalaryList:List[Int], var salary:Int)

    def getSubordinatesSalary(node:Node, depth:Int):List[Int] = {
        if(node.subordinateList.isEmpty && depth==0)List.empty[Int]
        else if(node.subordinateList.isEmpty && depth!=0)node.salary::Nil
        else {
            val subordinateList = node.subordinateList flatMap {node => getSubordinatesSalary(node,depth+1)}
            if(depth!=0) node.salary::subordinateList else subordinateList
        }
    }

    def pascal(k:Int) = {
        def fact(num:Int):Int = if(num==0) 1 else num * fact(num-1)
        (0 to k) foreach{n =>
            (0 to n) foreach {r =>
                println(fact(n)/(fact(r)*fact(n-r)))
            }
        }
    }

    def sierpinski(n: Int) {
        def star(n: Long) = if ((n & 1L) == 1L) "1" else "_"
        def stars(n: Long): String = if (n == 0L) "" else star(n) + "_" + stars(n >> 1)
        def spaces(n: Int) = "_" * n
        ((1 << n) - 1 to 0 by -1).foldLeft(1L) {
            case (bitmap, remainingLines) =>
                println(spaces(remainingLines) + stars(bitmap))
                (bitmap << 1) ^ bitmap
        }
    }

    def func() = {
        def merge(p:String,q:String,i:Int,j:Int):String = {
            if(i==p.length && j==q.length)""
            else p.charAt(i).toString + q.charAt(j).toString + merge(p,q,i+1,j+1)
        }
        val p = Console.readLine()
        val q = Console.readLine()
        println(merge(p,q,0,0))
    }

    def supdig() = {
        def getDigitSum(num:Double):Double = {
            if(num<10) num
            else num%10 + getDigitSum((num-(num%10))/10)
        }
        def superdigit(num:Double):Double = {
            if(num<10) num
            else superdigit(getDigitSum(num))
        }
        val nk = Console.readLine()
        val N = nk.split(" ")(0)
        val K = nk.split(" ")(1).toInt
        val parts:List[String] = ((0 to N.length/9) map {i:Int => if(9*(i+1) < N.length) N.substring(9*i,9*(i+1)) else N.substring(9*i)}).toList
        val nsum = parts.filterNot(_ == "").foldLeft(0.0)((m:Double,n:String) => m + getDigitSum(n.toDouble))
        val sum = K * nsum
        println(superdigit(sum).toInt)
    }

    def listgcd() = {
        def findgcd(list:List[mutable.LinkedHashMap[Int,Int]]):List[Int] = {
            def findminpower(key:Int,list:List[mutable.LinkedHashMap[Int,Int]]):Int = {
                list match {
                    case x::Nil => x.getOrElse(key,0)
                    case x::x1 => x.getOrElse(key,0) min findminpower(key,x1)
                }
            }
            list.head.flatMap{(entry:(Int,Int)) =>
                val min = findminpower(entry._1,list)
                if(min!=0) entry._1::min::Nil else Nil
            }.toList
        }

        def formMap(list:List[Int]):mutable.LinkedHashMap[Int,Int] = {
            list match {
                case x::x1::x2 => mutable.LinkedHashMap(x -> x1) ++ formMap(x2)
                case Nil => mutable.LinkedHashMap.empty[Int,Int]
            }
        }

        val q = Console.readInt
        val listMap = (1 to q).map {i =>
            val line = Console.readLine().split(" ").toList.map(_.toInt)
            formMap(line)
        }.toList
        println(findgcd(listMap).foldLeft("")(_+_+" "))
    }

    def swapString() = {
        val N = Console.readInt()
        (1 to N) foreach{n =>
            val line = Console.readLine()
            println((0 to line.length-2 by 2).foldLeft("")((s:String,i:Int) =>
                s + line.charAt(i+1).toString + line.charAt(i).toString
            ))
        }
    }

    def compress = {
        def encode(list:List[Char]):String = {
            list match {
                case x::x1 =>
                    val tuple = list.span(_ == x)
                    val enc:String = tuple._1.length match {
                        case 1 => x.toString
                        case n => x.toString + n
                    }
                    enc + encode(tuple._2)
                case Nil => ""
            }
        }
        val line = Console.readLine()
        encode(line.toList)
    }

    def prefix = {
        val x = Console.readLine()
        val y = Console.readLine()
        val min = x.length min y.length
        val pList = (0 to min-1).takeWhile(i => x.charAt(i) == y.charAt(i))
        val p = x.substring(0,pList.length-1)
        println(pList.length+" "+p)
        val xdash = x.substring(pList.length)
        println(xdash.length + " "+xdash)
        val ydash = y.substring(pList.length-1)
        println(ydash.length + " "+ydash)
    }

    def sumOfPowers() = {
        def root(x:Int,n:Int) = Math.round(Math.pow(Math.E, Math.log(x)/n))
        def findSum(x:Int,n:Int,nthroot:Int,d:Int):Int = {
            if(x==0) 1
            else if (x<0) 0
            else{
                var sum = 0
                for(i <- d to nthroot){
                    print(x+":"+i)
                    val rem = (x - Math.pow(i,n)).toInt
                    sum += findSum(rem,n,root(rem,n).toInt,i+1)
                }
                println()
                sum
            }
        }
        val X = Console.readInt()
        val N = Console.readInt()
        val nthroot = root(X,N)
        println(findSum(X,N,nthroot.toInt,1))
    }

    def main(args:Array[String]){
        val nq = Console.readLine().split(" ")
        val N:Int = Integer.parseInt(nq(0))
        val tree:List[Node] = List.fill(N)(Node(Nil,Nil,0))
        val Q:Int = Integer.parseInt(nq(1))

        (1 to N-1) foreach { i =>
            val line = Console.readLine()
            val u:Int = Integer.parseInt(line.split(" ")(0))
            val p:Int = Integer.parseInt(line.split(" ")(1))
            tree(p-1).subordinateList = tree(u-1) :: tree(p-1).subordinateList
        }

        val line = Console.readLine()
        val salaryList:List[Int] = line.split(" ").map(Integer.parseInt(_)).toList
        var salaryEmployeeMap = Map[Int,Int]()
        (1 to salaryList.length) foreach {i =>
            tree(i-1).salary = salaryList(i-1)
            salaryEmployeeMap += (salaryList(i-1) -> i)
        }

        (0 to N-1) foreach {i =>
            val salaryList:List[Int] = getSubordinatesSalary(tree(i),0)
            tree(i).subordinateSalaryList = salaryList//.sorted
        }

        var d = 0
        (0 to Q-1) foreach {i =>
            val line = Console.readLine()
            val v = Integer.parseInt(line.split(" ")(0))
            val k = Integer.parseInt(line.split(" ")(1))
            if(!tree(v+d-1).subordinateSalaryList.isEmpty){
                val kthLowestSalary:Int = tree(v+d-1).subordinateSalaryList(k-1)
                d = salaryEmployeeMap.get(kthLowestSalary).get
            }
            println(d)
        }
    }
}
