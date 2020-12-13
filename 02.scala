import scala.io.Source
import scala.AnyRef
/*
 * this one is basically the same question as aoc01
 * just looking at combinations of 3 values instead
 * of looking at combinations of 2
 */

object aoc02 extends App{
  var acc = 0;
  val arr : Array[Int] = Source.fromFile("inputs/day1").getLines.map( _.toInt).toArray
  val len = arr.size-1;
  for ( i <- 0 to len ){
    for( j <- i+1 to len ){
      for( k <- j+1 to len ){
        if ( (arr(i)+arr(j)+arr(k) ) == 2020 ){
          println(arr(i) * arr(j) * arr(k));
          sys.exit
        }
      }
    }
  }
  println("no numbers that add to 2020");
}
