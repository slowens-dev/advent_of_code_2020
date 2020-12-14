import scala.io.Source
/*
 * this one is essentially identical to aoc05 except we are doing multiple
 * traversals with different schemas, simply make a generic traversal method
 * that will take in the schema, then use the operations again using the correct 
 * shema for each iteration and take the product of the results
 */

object aoc06 extends App{
  def rideSlope(d_x:Int, d_y:Int, arr:Array[String]): BigInt ={
    val arr_len = arr.length
    val line_len = arr.head.length
    var acc = 0; var x = 0;
    for( i <- 0 to arr_len-1 by d_y){
      if (arr(i)(x) == '#') acc += 1;
      x = (x+d_x) % line_len
    }
    return acc;
  }
  val arr: Array[String] = Source.fromFile("inputs/day3").getLines.toArray
    println(List(
      rideSlope(1, 1, arr),
      rideSlope(3, 1, arr),
      rideSlope(5, 1, arr),
      rideSlope(7, 1, arr),
      rideSlope(1, 2, arr)
    ).product)
}
