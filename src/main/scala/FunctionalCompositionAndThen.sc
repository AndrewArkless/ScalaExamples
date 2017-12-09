val numbersList=List(1,2,3,4,5)

def addOne(l:List[Int])={
  println("A")
  l.map(_+1)
}

def mulByTen(l:List[Int])={
    println("B")
    l.map(_*10)
}
//val mulBy5=new Function[List[Int]{
//  override def apply(v1:List[Int])=
//    {
//      l.map(_*5)
//    }
//}

val addOneAndMul10 =addOne _ andThen mulByTen _
val comp=addOne _ compose mulByTen _
addOneAndMul10(numbersList)
comp(numbersList)