///**
//  * Created by andrew on 17/11/17.
//  */
//object CurryTest extends App {
//  def modN(n: Int)(x: Int) = ((x % n) == 0)
// object x {
//   def filter(xs: List[Int], p: Int => Boolean): List[Int] =
//     if (xs.isEmpty) xs
//     else if (p(xs.head)) xs.head :: filter(xs.tail, p)
//     else filter(xs.tail, p)
//
//
//   val x: (Int) => Boolean =modN(2)
//
//
//   val nums = List(1, 2, 3, 4, 5, 6, 7, 8)
//   println(filter(nums, xx))
//   println(filter(nums, modN(3)))
// }
//}