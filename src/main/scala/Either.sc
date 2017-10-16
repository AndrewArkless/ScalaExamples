/****** EITHER ******/
/*This is a wrapper type much like option and can be used
when a function can return one of two results. A result can be
wrapped in a Right or a Left type. The convention is to return a
Left in the case of a fail or "unhappy path" type and Right
for a success. Usefull if you want your function to repo
 */

/* Suppose you want a function which gets takes user name and
if the criteria is met, returns secret data, but otherwise returns
something which says it's unauthorized.
 */


def divide(numerator:Int,denominator :Int): Any ={
  if (denominator==0) "Can't divide by zero!"
  else numerator/denominator
}

//divide(20,10)+1  Won't compile what is the type!

def divideEither(numerator:Int,denominator :Int): Either[String, Int] ={
  if (denominator==0) Left("Can't divide by zero!")
  else Right(numerator/denominator)
}

divideEither(20,10) match {
  case Left(i)=>i
  case Right(i)=>i+1
}

//divideEither(20,10)+1
/* Suppose you want a function which gets takes user name and
if the criteria is met, returns secret data, but otherwise returns
something which says it's unauthorized.
 */

case class unauthorized(unauthoriedName:String,timeOfAccess:String)
case class authorized(authorizedName:String,someDate:String)

def allowAccess(name:String)={
  if (name=="Andrew") authorized("Andrew","Some secretData!")
  else unauthorized(name,"Today!")
}
val x=allowAccess("Andrew")

def allowAccessE(name:String): Either[unauthorized, authorized] ={
    if (name=="Andrew") Right(authorized("Andrew","Some secretData!"))
    else Left(unauthorized(name,"Today!"))
}

allowAccessE("Andrew") match {
  case Left(x)=>println(x.unauthoriedName,x.timeOfAccess)
  case Right(x)=>println(x.authorizedName,x.someDate)
}
