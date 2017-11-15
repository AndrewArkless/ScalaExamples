def aValueType[A<:AnyVal](a:A)={
  (a,a)
}

def aRefType[A<:AnyRef](a:A)={
  (a,a)
}
// aValueType(bob) error passing Ref type
aValueType(1) // :-)

aRefType(bob) // :-)
//aRefType(1) error passing a val type

class Animal(n:String)
class Mammal(n:String) extends Animal(n)
class Dog(n:String) extends Mammal(n)

val dog=new Dog("Fido")
val mammal=new Mammal("Rover")
val animal=new Animal("Spot")

def acceptAnimal[A<:Animal](a:A)={
  (a,a)
}
acceptAnimal(dog)
acceptAnimal(mammal)
acceptAnimal(animal)

def acceptMamal[A <:Mammal](a:A)={
  (a,a)
}
acceptMamal(dog)
acceptMamal(mammal)
//acceptMamal(animal)

def acceptMamalAndAbove[A >: Mammal](a:A)={
  (a,a)
}

acceptMamalAndAbove(mammal)
acceptMamalAndAbove(animal)
acceptMamalAndAbove(dog)