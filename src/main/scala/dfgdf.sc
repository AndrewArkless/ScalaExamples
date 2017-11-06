//def a(x:Int,y:Int,z:Int)={
//    b(x,y,z)
//  }
//
//
//
//
//
//def b(x:Int,y:Int,z:Int,n:Int,m:Int)={
//    x+y+z+n+m
//  }
//
//val x: (Int, Int) => Int

//def mul(x:Int=20,y:Int=10,z:Int=30)={
//  x+y-z
//}
//mul(x=200,z=30)
//

def q ={
  val x:Option[BigDecimal]=Some(2.01)
  None
}

q.map{x=>x.toString}.getOrElse("")

