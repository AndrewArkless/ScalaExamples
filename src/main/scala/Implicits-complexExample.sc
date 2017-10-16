/***********************Implicits Magic Code **************************

* Adapted from an Example in Programming Scala by D Wampler & Alex Payne
*
*Senario is you want to calculate the tax on your salary but there are variations in the tax rate
*So there is a uk base rate and regional variations. You want to be able to calculate
*the rate if it was just the UK base rate and calculate the rate if it was Scotland where
*the taxRate is the Uk base rate plus 10%
*
*/


/*this is the basic function note that the implicit rate is curried */

 def calculateTax(salary:Int)(implicit taxRate:Int)={
   (salary/100)*taxRate
 }
/////////////////////////////////////////////////////////////////////////////

/* simple object for the UK rate and defines an implicit value to hold the uk rate */
 object UKTax{
  implicit val rate=20
 }

////////////////////////////////////////////////////////////////////////////

/* more complex object to encapuslate the regional variations

 the rate takes implicitly a case class which defines the baseRate and
 the region code which is then calculated and returned. But note the

 */
 case class RegionalTax(baseRate:Int,regionCode:String)

 object RegionalTax{
   private def extraTaxRateForRegion(regionCode:String):Int={
     regionCode match {
       case "en" => 0
       case "sc" => 10
       case "wa" => 20
       case "ni" => 0
     }
  }
  implicit def rate(implicit regionalTax:RegionalTax):Int={
    regionalTax.baseRate + extraTaxRateForRegion(regionalTax.regionCode)
  }
 }

//////////////////////////////////////////////////////////////////////////

 {
  import UKTax.rate
  val amount=37000
  println(s"Tax on Uk ${calculateTax(amount)}")
 }

 {
  import RegionalTax.rate
  implicit val myRegion=RegionalTax(20,"sc")
  val amount=37000
   /*Note the call to the calculateTax amount is the same, as
   but the context resolves the implicits so it is calculating using
   the appropriate rates*/

  println(s"Tax on Regions ${calculateTax(amount)}")
 }

