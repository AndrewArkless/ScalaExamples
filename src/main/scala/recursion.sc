
///*************Recursion ******************///

/* Recursion is a powerful way of iterating over data,
by the repeated application of an algorithm
 */


//simple example just to add a list of numbers up
//you would never bother to do this normally as you
//can just use the sum function on list.


val numList=List(1,2,3,4,5,6,7,8,9,10)


/*A recursive function - it calls itself,
note we MUST define the return type of the function

A recursive funtion would normally consist of two components
one would be the terminating condition which defines when
to stop

Here we interate over a list of Ints. A list has the useful feature of us being able to call
 Head on it to give us the first value and tail which gives the rest of the list

 numList.head=1
 numList.tail=List(2,3,4,5)

 an empty list is represented by Nil

*/

def sum(l:List[Int]):Int={
  if (l==Nil) 0 //terminating condition and force the call to unwind and calculate.
  else l.head + sum(l.tail)
}

/* when this function is called it takes the head (1) and constructs an expression
which adds (1) it to the the sum function being called on the tail, which takes the head now 2
and then adds it to the tail of that List(3,4,5) and so on until the there is no tail left
and the list evaluates as Nil and we return 0 and the expression is complete and then can be evaluated

so first step
1+(sum(l.tail) tail=2::3::4::5::Nil
1+2+(sum(l.tail) tail=3::4::5::Nil
1+2+3+(sum(l.tail) tail= 4::5::Nil
1+2+3+4+(sum(l.tail) tail=5:Nil
1+2+3+4+5+(sum(l.tail) tail=Nil
1+2+3+4+5+0 = 15 and evaluate!
*/

sum(numList)

/*this is great as far is it goes, note the symbol in the margin the twirl,this shows that it
is recussive but it is showing us that it not "TAIL recursive", this means it doesn't take
advantage of Scala tail recursive feature.

As it stands if the number of elements that the function iterates over and the number of calls to
function get too many there will eventually be a stack over flow exception.
 */

/*if we write it in a tail recursive manner scala can optimise it so the error doesn't occur.

If we look at the normal recursive way, we notice that the result is not evaluated until the very end
when the terminating condition is met and the expression is complete and the function call as it were
unwinds and evaluates it
 */

/*tail recurssion is a different way of writing so the result is built up as the recurssion is complete
and the only think left to do when the terminating condition is met is to throw up the final result
To achieve this we often have to write a helper function takes an accumulator which passes the result
of the function into the next call of the function which then adds to its evaluation to it and passes
it on down and so on.
 */
import scala.annotation.tailrec

def sumRecursive(l:List[Int]):Int={
  @tailrec
  def helper(l:List[Int],accumulator:Int):Int={
    if (l==Nil) accumulator //terminating condition just return the result
    else helper(l.tail,accumulator+l.head)
  }
  helper(l,0) //<- the 0 to initialise the accumulator
}



/*
each call will look something like this
1::2::3::4::5::Nil, 0
2::3::4::5::Nil,    1   (1+0)
3::4::5::Nil,       3   (1+2)
4::5::Nil,          6   (3+3)
5:Nil,              10  (6+4)
Nil,                15  (10+5)
and return the result - 15
 */

sumRecursive(numList)

/* when a function is tail recursive it will have a curved arrow instead of a twirl.
We can also annotate the function as tail recurisve with @tailrec which means it will not
compile if is changed so it
 */

def sumTailRecursive(l:List[Int]):Int={
  @tailrec
  def helper(l:List[Int],accumulator:Int):Int={
    l match {
      case Nil => accumulator
      case h :: t =>helper(t, accumulator + h)
    }
  }
  helper(l,0) //<- the 0 to initialise the accumulator
}

sumTailRecursive(numList)

// the weird h :: t is a way of pattern matching on lists, the h and t are merely convention but
// it means the list has a pattern of a head and a tail, h refers to the head, the t refers to the rest of the list
// which could be either a value or NIL the :: is a way of constructing a List like this

val l=1 :: 2 :: 3 :: Nil

