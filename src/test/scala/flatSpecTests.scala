import org.scalatest._
import TestFunctions._

class flatSpecTests extends FlatSpec with Matchers {
  "Calling myFunction" should "return 2 when 1 and 1 are passed in" in {
      myFunction(1,1) should be (2)
    }

  "Calling myFunction" should "return a zero execption a 0 is passed" in {
    a [ZeroException] should be thrownBy(myFunction(0,0))
  }

  "Calling myFunction" should "return 5 when 2 and 3 are passed in" in {
    assert(myFunction(2,3) == 5)
  }

  "Calling myFunction" should "return 5 when 3 and 3 are passed in" ignore {
    assert(myFunction(3,3) == 5)
  }

  "Calling Random number" should "return a number between 1 and 10" in {
    for(a<-1 to 100) {
      randomNumber should (be >= 0 and be <= 10)
    }
  }
}
