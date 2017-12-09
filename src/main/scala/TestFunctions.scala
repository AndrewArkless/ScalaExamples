/**
  * Created by andrew on 09/12/17.
  */
object TestFunctions {
  class ZeroException extends Exception
  def myFunction(x:Int,y:Int): Int ={
    if (x==0) throw new ZeroException
    else x+y
  }

  def randomNumber={
    scala.util.Random.nextInt(10)
  }
}
