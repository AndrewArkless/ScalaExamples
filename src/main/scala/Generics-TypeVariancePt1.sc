
/*
Generics very basic

Suppose we have a function which selects a random value from a List,

*/
def randomName(names: Seq[String]): String = {
  val randomNum = util.Random.nextInt(names.length)
  names(randomNum)
}

/*

This of course will only handle Seq's of strings
 */
val names = Seq("Aleka", "Christina", "Tyler", "Molly")
val winner = randomName(names)

def randomElementGeneric[A,B](seq: Seq[A]):A  = {
  val randomNum = util.Random.nextInt(seq.length)
  seq(randomNum)
}

randomElementGeneric(Seq("Aleka", "Christina", "Tyler", "Molly"))
randomElementGeneric(List(1,2,3))
randomElementGeneric(List(1.0,2.0,3.0))
randomElementGeneric(Vector.range('a', 'z'))

/*
Normally when we want to pass arguments to a function we have to
specify the type of the arguements we take in Int,String etc.

def add(a:Int,b:Int)= a+b
 */

//Suppose we a requirement to create one function which is can add together any types
//of entities char,double,case classes, ints, strings.

//Lets define a basic function for Ints.

def addInts(x:Int,y:Int)=x+y

addInts(1,2)
//great, but how is this going to work for doubles

//addInts(1.2,2.3) obviously this isn't going to compile

def addDoubles(x:Double,y:Double)=x+y

addDoubles(1.0,2.0)

//but we only wanted to call one thing and now we are going to have
//to write a function for each type.

//we could write a generic function

/*Here we have defined a generic function which takes handles types A and B
  A and B are just convention A doesnot mean Any. What this is saying is A/B could
 be any type Int, Double, case class, function. So in this instance if we A could mean
 int and B string. So this function will take two ints and return a string, or two doubles
 and return an Int.

def addStuff[A,B](x:A,y:A):B={
  x + y //?? this will not work
}


 because we are dealling with generics x+y will not work, as the compiler
 has no idea how do an addition on something where the type is not defined.

*/
/*
so lets allow give it an additional paramter where we
can pass in a function which will perform an addition on a given
type. Note that this function is also declared as generic, and will take
the parameters x and y,of type A and return type B. Note how they match up.
 */
def addStuff[A,B](x:A,y:A,f:(A,A)=>B)={
  f(x,y) //?? this will not work
}

//Let us declare a function which adds to Ints and converts them to a string

case class Person(name:String)
val bob=Person("Bob")
val laura=Person("Laura")

val addIntsToString=(x:Int,y:Int)=>"The answer is "+ (x+y)
val addPersons=(x:Person,y:Person)=>"Here come " + x.name +  " " + y.name
addStuff(1,2,addIntsToString)
addStuff(bob,laura,addPersons)

/*

Straying of generics we are still having to push explicitly the function, lets deal with that.
Implicits to the rescue....

*/


implicit val addIntsToStringI=(x:Int,y:Int)=>"The answer is "+ (x+y)
implicit val addPersonsI=(x:Person,y:Person)=>"Here come " + x.name +  " and " + y.name

def addStuffI[A,B](x:A,y:A)(implicit f:(A,A)=>B)={
  f(x,y)
}
addStuffI(1,2)
addStuffI(bob,laura)



