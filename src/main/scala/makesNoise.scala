/**
  * Created by andrew on 09/12/17.
  */
trait hasName

trait GetIntName extends hasName{
  def getName:Int
}

trait GetStringName extends hasName{
  def getName:String
}

trait  MakesNoise {
  def noise:String

}

class Dog(name:String) extends MakesNoise with GetStringName
{
  def noise="Woof"
  def getName="Rover"
}

class Cat(name:String) extends MakesNoise with GetIntName{
  def noise="meow"
  def getName=1245
}

class Bird (name:String)extends MakesNoise with GetIntName{
  def noise="tweet"
  def getName=8888
}

class Lion(name:String) extends MakesNoise with GetStringName{
  def noise="Roar"
  def getName="Aslan"

}
