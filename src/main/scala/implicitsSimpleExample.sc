object UKTax{
  implicit val rate=20.0
}

object USTax{
  implicit val rate=15.0
}


def calcTaxDue(wage:Double)(implicit taxRate:Double)={
  (wage/100)*taxRate
}

{
  import UKTax.rate
  calcTaxDue(25000.0)
}

{
  import USTax.rate
  calcTaxDue(25000.0)
}