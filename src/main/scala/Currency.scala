/**
  * Created by andrew on 09/12/17.
  */
trait Currency {
  def name: String
  def symbol: String
}

case object EUR extends Currency {
  val name = "EUR"
  val symbol = "€"
}

case object USD extends Currency {
  val name = "USD"
  val symbol = "$"
}