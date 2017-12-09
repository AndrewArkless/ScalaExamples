/**
  * Created by andrew on 09/12/17.
  */

object test extends App{
  println("Hello World")

  val dog=new Dog("Rover")
  dog.noise
  val cat=new Cat("Top Cat")
  val bird=new Bird("Hedwig")

  val lion =new Lion("Lenny")

  def noise(m:MakesNoise)={
    println(m.noise)
  }
  val animals=List(dog,cat,bird,lion)
  animals.map(noise)

  def something(n:MakesNoise)={
   println(
    n match {
      case n:Dog=>"Dog"
      case n:Cat=>"Cat"
      case n:Bird=>"Bird"
      case n:Lion=>"Lion"
    }
   )
  }

  def getName(n:hasName): Unit ={
    println(
      n match {
        case n:Dog=>n.getName
        case n:Cat=>n.getName
        case n:Bird=>n.getName
        case n:Lion=>n.getName
      }
    )
  }
  animals.map((something))
  animals.map((getName))

  animals.collect{case n:GetIntName=>println(n.getName)}
}
