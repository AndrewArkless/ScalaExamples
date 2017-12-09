
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.mockito._
import org.mockito.Mockito._

/**
  * Created by andrew on 09/12/17.
  */
class DependencyTest extends WordSpec with Matchers with MockitoSugar{
  "calling Books" should {
    "Return Lord of The Rings" in {
      val mockBookSevice=mock[BookService]
      when(mockBookSevice.getAllBooks).thenReturn ("Alien")
      val result=new DisplayBooks{
        override def bookService=mockBookSevice
      }
      result.displayBooks shouldBe "Alien"
    }
  }

}
