import scala.io.Source
/*
 * this one is really simple, traverse the page via the given schema,
 * :: down one and to the right 3, using modulus on the rightward shifts
 * because each line repeats itself to the right. countht the number of 
 * "#" you encounter
 */

object aoc05 extends App{
  var acc = 0;
  var x = 0;
  Source.fromFile("inputs/day3").getLines.foreach((line:String) => {
    if (line(x) == '#') acc += 1;
    x = (x+3) % line.length
  })
  println(acc);
}
