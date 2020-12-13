import scala.io.Source
/*
 * this one is very simple, find 2 numbers in the file that add to 2020
 * and return their product, only one way to do it, add every possible 
 * combination of values, when you find one that adds up to 2020 then
 * take the product of those two values and break the loop
 */

object aoc01 extends App{
  var acc = 0;
  val arr : Array[Int] = Source.fromFile("inputs/day1").getLines.map( _.toInt).toArray
  for ( i <- 0 to arr.size-1 ){
    for( j <- i+1 to arr.size-1 ){
      if ( (arr(i)+arr(j) ) == 2020 ){
        println(arr(i) * arr(j));
        sys.exit
      }
    }
  }
  println("no numbers that add to 2020");
}
