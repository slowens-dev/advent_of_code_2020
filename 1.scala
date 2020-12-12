import scala.io.Source

object aoc1 extends App{
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
