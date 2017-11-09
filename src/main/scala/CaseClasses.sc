/*** CASE CLASSES ****/


class cPerson(val name:String,val age:Int, val address:String) {
}


val cEric=new cPerson("Eric",83,"London")

//
//cEric match {
//  case cPerson("Eric",_,_)=>"mm"
//}

case class Person(name:String,
                  age:Int,
                  address:String){
  def multiplyAge=age*2
}

/*
  all the parameters are the constructor
  you can still create methods ont the case class like you would with classes
*/


val eric=Person("Eric Blair",84,"London")
//no New is required as case classes have an apply method automatically
//created

//Getters are created automatically
eric.name
eric.multiplyAge
//eric.name="George Orwell" won't compile no setters are generated as
//all the parameters are Vals and can't be changed

//you can create a mutable case class by declaring
//the values as var

case class mutablePerson(var name:String,
                         var age:Int, var address:String)

val justDontDoThis=mutablePerson("Eric Blair",84,"London")

//you can then change the name as you like.
//note that we have data structure which has mutable
//parts but is still a value which can't be changed

justDontDoThis.name="George Orwell"

//Best way to "change" a value in the case class is to create another
//instance using the copy method and changing the value you need to
//change as you do so.

val george=eric.copy(name="George Orwell")

//case classes have an in built equality method which compares the case
//class paramaters.

//with normal classess
val cGeorge1=new cPerson("Eric",83,"London")
val cEric1=new cPerson("Eric",83,"London")


cGeorge1==cEric1 //this only tests memory location for the equality - so false

val georgeClone=cGeorge1
georgeClone==cGeorge1 //true as share same memory location.

val caseClassGeorge=Person("George",84,"London")
val caseClassAnotherGeorge=Person("George",84,"London")

caseClassGeorge==caseClassAnotherGeorge //true as because parameters match but they are
//different instances.


//you can pattern match on them which you can't easily do on normal
//classes
//here we have written some patterns which a person could match against
//
// where there is an underscore _ it means we don't care what this is
eric match {
  case Person("George Orwell",_,_)=>println("Its George!")
  case Person("Eric Blair",22,_)=>println("Its eric aged 22!")
  case Person("Eric Blair",84,_)=>println("Its eric aged 84")
  case _ => println("Who knows!")
}

//////////////////////////////////////////////////////

//with guards!
eric match {
  case Person(_,age,_) if age<30 =>println("Is under 30!")
  case Person(_,age,_) if age>=30 && age<=60=>println("Is between 30 and 60")
  case Person(_,age,_) if age>60 =>println("Is older than 60!")
  case _ => println("Who knows!")
}



