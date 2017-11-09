//----TUPLES-----

//Pair of values of either same or different types.
val t=("AAAA",22) //infers a tuple of String, Int

//A tuple can have upto 22 elements but no more.

//to access an element of a tuple use the following horrible syntax
//this can make your code look horrible.
t._1
t._2



//Why tuples - certain operations will yield them as a result

//Zipping with Index is one,
val alphabet=List('a','b','c','d','e')
alphabet.zipWithIndex


//functions such as partition will yield them
val n="New York"
val s="San Francisco"
val w="Washington"

def getCityCode(c:String): (String, String) ={
  c.partition(_.isUpper)
}

val result=getCityCode(w)

//but they are easy to pattern match on ...

result match {
case("W",_) =>println("WASHINGTON")
case("NY",_)=>println("NEWYORK")
case("SF",_)=>println("SANFRANCISO")
case(_,_)=>println("Nothin")
}

//got a some different values you want to match on
//bundle them into a tuple and do a match!

val age=21
val name="Bob"

(age,name) match {
  case(22,"Peter")=>println("Peter 22")
  case(21,"Paul")=>println("Paul 21")
  case(21,"Bob")=>println("Bob 21")
  case _ =>println("SOmeone else")
}

//Fancier operations

val l=List((1,2),(3,4),(5,6))
for((x,y)<-l) yield(x*y)

//the Unapply method on Case Classes result in an Option of a tuple type,
//thats how you can match on them easily.
