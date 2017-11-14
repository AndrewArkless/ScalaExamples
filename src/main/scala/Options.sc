
/*****************OPTIONS***************************************/

/*Use options to express where a value may or mayNot be present
 Safer than using a Null, less ambiguous than an EmptyString ("") and generally saves dealing
 with crap
*/

// using Null

def toInt(in:String) ={ //what is the return type of this?
  try{
    Integer.parseInt(in)
  } catch
   {
      case e: NumberFormatException => null
    }
}
val result=toInt("A")
if (result==null) {
  println("That didn't work")
} else{
  println(result)
}
/*
for this to work you had to know what a function
did if it couldnot evaluate it could equally have been a NullPointerException
or empty string so you have to know and therefore it violates functional purity */

/*Using an Option if the calc is successful it we will put the answer in a Some if not return a None*/

def toIntOpt(in:String): Option[Int] ={
  try{
    Some(Integer.parseInt(in))
  } catch {
    case e:Throwable => None
  }
}

val q =toIntOpt("2")

/*All the consumer needs to know about this function needs to know,
 is if it is an Option it will only return 1 of 2 results.
  */

/*handling an option for the consumer can be done in a number of ways*/

//Get

toIntOpt("2").get   //this simply gets the result out of the option if the option is indeed successful

/*toIntOpt("A").get However using a get where a None is return results in a Run Time Error. Get should only be
used when you are guaranteed 100% that your function will ALWAYS return a Some. But generally should be avoided
 */

//GetOrElse
/*Use a getOrElse instead so when you call a function which can return a None you
supply a value for the None
 */
toIntOpt("A").getOrElse("Cannot calculate")

//Pattern Match

toIntOpt("A") match {
  case Some(i)=>println(i) //here the mysterious i is just what the value of the option is bound to, it's name is up to you */
  case None=>println("That didn't work out did it!")
}

toIntOpt("2") match {
  case Some(i)=>println(i)
  case None=>println("That didn't work out did it!")
}


//Mapping

val qq=toIntOpt("23").map(x=>println(x))

//Folding
def multiplyBy2(i:Int)=i*2
toIntOpt("A").fold(0)(x=>multiplyBy2(x))
toIntOpt("1").fold(0)(x=>multiplyBy2(x))
toIntOpt("A").fold(0)(_)
//You can fold on an options where the first parameter is the default value if a none is returned
//Folding on an option is possible, but not popular as it is harder to read. There is debate
//about this, but you may still see examples in code.

/*Why options again */

// Suppose we have a list of strings we want to turn to ints using our function which returns null and add them up
// I know I can do this on List of numbers to tally them
List(1,2,3,4).sum

val numberList=List("1","2","3","4")

numberList.map(i=>toInt(i))
//sofar so good i've got a list of numbers! Lets stick sum ont the end to add them

//numberList.map(i=>toInt(i)).sum  (uncomment me to see me crash and burn!)

//hmm that doesn't work the sum function has no clue as to what type it is dealing with, look at
//the return type of the toInt function it is Any, so this isn't going to fly without a
// a lot of work and it gets worse
//look what happens when we stick a value which can't be evaluated...

val numbersWithNonIntsList=List("1","2","a","3","4")
numbersWithNonIntsList.map(i=>toInt(i))

//nasty there is a null in the middle. I just wanted a list of numbers. now
//I've got to get that crap out before I can even think of adding them all up ...

//Ok lets try the option function instead

numbersWithNonIntsList.map(i=>toIntOpt(i))

//well thats not much better I've got the Some's and Nones floating around this is surely worse!

/*Ok one last step replace Map with flatmap and ...*/

numbersWithNonIntsList.flatMap(i=>toIntOpt(i))
/* flatMap will go through the list, apply our function to each value which will return a Some/None
and will in-addition get Flatten the resultant lists of Somes and Nones to the actually values and where
Nones ares simply ignored...
 */

numbersWithNonIntsList.flatMap(i=>toIntOpt(i)).sum

/*or in shorthand */
numbersWithNonIntsList.flatMap(toIntOpt(_)).sum