//Enumerations using Enum

object CurrencyEnums extends Enumeration{
  type CurrencyEnums =Value
  val Euro,Zloty=Value
}

import CurrencyEnums._
case class Payment(amount:Int,c:CurrencyEnums)

val amount=Payment(111,Euro)

def getCurrency(c:CurrencyEnums.Value)={
  c match {
    case CurrencyEnums.Euro=>"EURO!"
    case CurrencyEnums.Zloty=>"Zloty!"
  }
}

getCurrency(amount.c)

//Enumerations using Case Objects

trait CurrencyObject{
  def getName:String
}

case object Pound extends CurrencyObject
{
  def getName="Pound!"
}
case object Dollars extends CurrencyObject {
  def getName="Dollars!"
}

case class AnotherPayment(amount:Int,c:CurrencyObject)

val myPayment=AnotherPayment(200, Pound)

def getPaymentCurrency(c:CurrencyObject)={
  println(
   c match {
      case Pound=>Pound.getName
      case Dollars=>Dollars.getName
    }
  )
}

getPaymentCurrency(myPayment.c)