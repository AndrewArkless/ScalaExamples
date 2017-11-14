///****************Implicits - aka Magic Code ********/
//
///*Implicit parameters - implicits can be used to supply arguments
//where they are missing
//
//Implicit parameters are useful for removing boiler plate parameter passing and
//can make your code more readable.
//So if you find yourself passing the same value several times in quick succession, they can help hide the duplication.
// */
//
////A function which takes an explicit parameter, and an implicit
////note the implicit is curried and comes at the end
//
def multiply(x:Int)(implicit y:Int)={
  x*y
}

////a function which only takes an implicit parameter
def addTwo(implicit x:Int)={
  2+x
}
def addThree(implicit x:Double)={
  3.0+x
}
////both can be called by passing parameters explicitly
multiply(10)(20)
addTwo(10)
addThree(2.01)



//or without the parameter explicitly as long as one is defined
//somewhere implicitly.
//The compiler will say my multiply function needs another Int
//and will look for any Int in scope which matches Int and will pass
//this in. So it will pass in someNumber value as it matches what
//it needs.
//we cannot however define another implicit variable as an implict int in this
//as it will not know which to pick and will generate an error.


  implicit val someNumber: Int = 30
  implicit val someDoubleNumber: Double = 40.0
  //implicit val anotherNumber: Int = 40

  multiply(10)
  addTwo
  addThree //takes someDoubleNumber as its type matches.




/////////////////////////////////////////////////////////////

/*Implicit Conversions
Often they can be used to convert one type to another when "behind the scenes"
 */
//suppose we have two classes which represnt journeys and we want to
//add them together
case class Miles(miles:Double,start:String,Destination:String){
  def + (anotherJourney:Miles)={
    println("MILES")
    this.miles+anotherJourney.miles
  }
}

case class Kilometers(km:Double,start:String,Destination:String){
  def + (anotherJourney:Kilometers)={
    println("KM")
    this.km+anotherJourney.km
  }
}

val newcastleToLondon=Miles(300.0,"Newcastle","London")
val LondonToBrighton=Kilometers(50.0,"London","Brighton")

//I want to total the journey from London to Newcastle but
//because I have differing types this isn't going to work there is
//the add function in the case class only accepts a Miles type
//newcastleToLondon+londonToBrighton

/*We could create our own function to do this like this */
def kmToMiles(k:Kilometers)={
  val miles=k.km / 1.6
  Miles(miles,k.start,k.Destination)
}
newcastleToLondon+kmToMiles(LondonToBrighton) + " miles"

/*And this works fine but we have to explicitly call the kmToMiles
not the worst thing in the world if we declare it implicitly we get
rid of this and we have a more expressive way of adding without boiler plate
*/

implicit def kmToMilesImp(k:Kilometers)={
  val miles=k.km / 1.6
  Miles(miles,k.start,k.Destination)
}

println("newcastle to Brigton with Impl conversion "
  + (newcastleToLondon+LondonToBrighton) + " Miles")





//The compiler knows that the + method needs a miles class, and it knows we
//are supplying it a KM class, so it looks for a function which takes a
//a KM class and returns a miles class, in this instance finds out implicit
//method and invokes it.

/*However do you know what implicits are being invoked do you know what they
are doing!!!!!!!!!!!
 */


//Using Implicits to adding functionality to closed classes
//For example I want for some reason to have a function which
//can increment each character in a string by 1 so abc becomes bcd

def incrementMyString(s:String)={
  s.map(c => (c + 1).toChar)
}

incrementMyString("ABC")

//or by declaring it as implicit in it appears to become
// a part of  String.
// a good place to stick these is in a utils object and then just
//import it to where you want to use it.

object StringUtils {
  implicit class StringImprovements(val s: String) {
    def increment = s.map(c => (c + 1).toChar)
  }
}
import StringUtils._

val result="HAL".increment


////where Implicits are looked for
//First look in current scope
//Implicits defined in current scope
//Explicit imports
//  wildcard imports
//  Same scope in other files
//Now look at associated types in
//  Companion objects of a type
//Implicit scope of an argument’s type (2.9.1)
//Implicit scope of type arguments (2.8.0)
//Outer objects for nested types

