import org.scalatest.{FunSpec, Matchers}
import TestFunctions._


class funSpecTests extends FunSpec with Matchers{
 describe("myFunction")  {
   it("return 2 when 1 and 1 are passed")  {
     myFunction(1,1) shouldBe 2
   }

   it("return 5 when 2 and 3 are passed") {
     assert(myFunction(2,3)==5)
   }

   it("should throw a zero exception when 0 is passed in") {
     an [ZeroException] should be thrownBy(myFunction(0,0))
   }
 }
}
