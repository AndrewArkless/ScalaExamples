
//Partially Applied Functions - not Partial Functions
//If partialFunctions have a body which only covers some of the inputs
//Partially Applied Functions are those where only some of the parameters are passed in.

def sum(a:Int,b:Int,c:Int): Int =a+b+c

sum(1,2,3)

// The form of this function can be thought of as
// (Int=>Int=>Int)=>Int
//suppose we can't supply all the params in one go
//we can supply the first param here and then pass _:Int for
//the missing params

val nextParam: (Int, Int) => Int =sum(1,_:Int,_:Int)


//This create a partiallyApplied function which we assign to nextParam
//and we can think of its type as (Int=>Int)=>Int and the body
//now as
//  1+b+c
// where the first value a is now replaced by the literal

val nextParam1: (Int) => Int =nextParam(2,_:Int)

//This create a partiallyApplied function which we assign to nextParam
//and we can think of its type as (Int)=>Int and the body
//now as
//  1+2+c
// where the first value a is now replaced by the literal

val complete=nextParam1(3)
// the final parameter is now supplied and the function
// is complete 1+2+3


//A use for this would be if you repeatedly are passing the
//same parameter to an argument it makes sense to be able
//to preload the function as it were. This allows a function
//to have a general use and then a more specific use.


def sendEmail(to:String,message:String)={
  println(s"Sending message $message to $to")
}

val sendMessageToBoss: (String) => Unit =sendEmail("my boss", _ :String)
//general message
sendEmail("co worker","Your code is great")

//specific
sendMessageToBoss("It wasn't my fault")
sendMessageToBoss("you are the BEST!")


//You can also pass around a function building up it's
//parameter as you go

val calcTax: (Int, Int, Int) => Int = (wage:Int, taxRate:Int, personalAllowance:Int)=>{
  ((wage-personalAllowance)/100) * taxRate
}

def setTaxRate (f:(Int,Int,Int)=>Int)={
  val taxRate=20 //some logic
  f(_:Int,taxRate,_:Int)
}

def setPersonalAllowance(f:(Int,Int)=>Int)={
  val pa=5000 //someLogic
  f(_:Int,pa)
}

def setWage(f:Int => Int)={
  //someLogic
  val wage=25000
  f(wage)
}

val taxDue=setWage(setPersonalAllowance(setTaxRate(calcTax)))


def sendEmailCurried(to:String)(message:String)={
  println(s"Sending message $message to $to")
  s"Sending message $message to $to"
}

sendEmailCurried("co worker")("Your code is great")
val emailManager: (String) => String =sendEmailCurried("Manager")_
emailManager("Pay rise!")

def add(x:Int,y:Int,z:Int)={ x+y+z}

val curriedAdd=(add _).curried
val xx=curriedAdd(1)
val yy=xx(2)
yy(3)


