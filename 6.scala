import scala.io.Source
import scala.AnyRef

object aoc1 extends App{
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
