/*
In mathematics, a function[1] is a relation between a set of inputs and a set of permissible outputs
with the property that each input is related to exactly one output. An example is the function that relates each
number x to its square x2
*/

//A complete function for ALL legit values of an Int there will be
//one and only one output. This function is complete
def add1(i:Int): Long ={
  i.toLong+1
}

// A partial function is a function that does not provide an answer for every possible input value it can be given.



//Heres a function which will break when it is passed a 0

val divide = (x: Int) => 42 / x

//divide(0)

//we could rewrite it so it is
val divide1=(x:Int)=> {
    if (x==0) "Can't accept 0" // or throw an error
    else 42/x // or we could wrap in an Option
}

//or we could right the function as a Partial which can explicitly state the domain of values
//it can handle.

val dividePF = new PartialFunction[Int, Double] {
  def apply(x: Int): Double = 42 / x
  def isDefinedAt(x: Int):Boolean = x != 0
}

// The apply method is the method which is called when a new class is created, and here we
// give it the body of our function.
// IsDefined at here says that I will handle all Ints except 0
// When we declare the Partial Function we also state the types it is using [Int,Int]
// here we are saying I'm going to take and Int and return an Int.

// what we can do is call isDefined before calling the function like this

//A PF however is more commonly written in the format

val dividePF2: PartialFunction[Int,Double] = {
  case d: Int if d != 0 => 42 / d
}

//rewriting this as a case statement is the equivalent of what is gone on before




//on its own this probably doesn't look that impressive, but here is a problem it can solve
//suppose you have a list of Ints and you want to apply the divide to them you could try

//List(0,1,2).map{divide} this blows up cos 0 is being passed in!

//however there is a neat alternative to map called collect which users Partial Functions

List(0,1,2) collect dividePF2

//What happens here is the collect method will check behind the scenes the isDefined method
//to see if the function can handle the value and if it can will execute the function against it
//if it can't it just ignores it and moves to the next one and so on...

//Another example is that you can filter on type in the example below the
//function can only handle Ints, so Strings are ignored.

List(42, "cat") collect { case i: Int => i + 1 }



//but the power comes with PF is that they can be chained together
//So lets say we want to go over a list and return which is odd,
//we can define a partial Function and call collect. This does what
//we want and ignores values that don't meet.

def isOdd:PartialFunction[Int,String]={
  case i:Int if (i%2 !=0) => s"$i is Odd!"
}

val nums=List(1,2,3,4,5,6,7,8,9,10)
nums collect isOdd

//But suppose now we want to not only write out the odd numbers but also write out the even

def isEven:PartialFunction[Int,String]={
  case i:Int if (i%2 ==0) => s"$i is Even!"
}

val numsCollect=nums collect {isOdd orElse isEven}

//what this is saying is try to apply the isODD to the value, if it is
//not defined then try the IsEven.

//as the composition of IsOdd and IsEven is effectively a complete function
//then we can map across it.

val numsMap=nums map {isOdd orElse isEven}

//If the function is called with a disallowed value, it will typically crash,
//yield a special return value,
// or throw an exception (and this should better be documented)

val isOne:PartialFunction[Int,String]={
  case 1=>"Is One"
}
val isTwo:PartialFunction[Int,String]={
  case 2=>"Is Two"
}
val isThree:PartialFunction[Int,String]={
  case 3=>"Is Three"
}
val neither:PartialFunction[Int,String]={
  case i @ _ => s"$i neither"
}

val oneTwo=List(1,2,3,4)
val isOneOrTwo = {isOne orElse isTwo}
val isOneOrThree ={isOne orElse isThree}
oneTwo collect {isOneOrTwo orElse neither}
oneTwo collect {isOneOrThree orElse neither}


val convertFn: PartialFunction[Any, Int] = {
  case i: Int => i;
  case s: String => s.toInt;
  case Some(s: String) => s.toInt
}

List(0, 1, "2", "3", Some(4), Some("5"),6.5, None, null, Unit).
  collect(convertFn)
