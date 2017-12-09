//Using Try for Functionally handling exceptions.

import scala.util.{Failure, Success, Try}

def divide(n:Int): Try[Int] ={
  Try{
    10/n
  }
}

divide(1) match {
  case Success(x)=>x
  case Failure(e)=>e
}

