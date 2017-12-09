/**
  * Created by andrew on 09/12/17.
  */
import org.scalatest._
import TestFunctions._

class wordSpecTests extends WordSpec with Matchers {
  "Calling myFunction" should {

    "return 2 when 1 and 1 are passed in" in {
      myFunction(1, 1) should be(2)
    }

    "return a zero execption a 0 is passed" in {
      a[ZeroException] should be thrownBy (myFunction(0, 0))
    }

    "return 5 when 2 and 3 are passed in" in {
      assert(myFunction(2, 3) == 5)
    }

    "return 5 when 3 and 3 are passed in" ignore {
      assert(myFunction(3, 3) == 5)
    }
  }
}