def transform[A,B](x:A,y:A)
                  (implicit f:(A,A)=>B): B ={
  f(x,y)
}

implicit val transformInts= (x:Int, y:Int)=>x*y + "Multiplied"
implicit val transformDoubles=(x:Double,y:Double)=>x-y +"Minus"
implicit val transformStrings=(x:String,y:String)=>x+y + "Added"

transform(1,2)
transform("A","B")
transform(2.3,5.5)

case class Animal[A,B](first:A,second:B)

/************************GENERICS*************************/

/*
Normally when we want to pass arguments to a function we have to
specify the type of the arguements we take in Int,String etc.

def add(a:Int,b:Int)= a+b
 */

//Suppose we a requirement to create a function which is

