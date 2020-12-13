import scala.io.Source
/*
 * this one is a piece of cake, the strings can be converted into binary and parsed into an int
 * simply return the largest int
 */

object aoc09 extends App{
  var max = 0;
  Source.fromFile("inputs/day5").getLines.foreach( (line:String) => {
    var n = Integer.parseInt( line.replaceAll("B", "1").replaceAll("F", "0").replaceAll("R", "1").replaceAll("L", "0"), 2)
    if (n > max) max = n;
  })
  println(max);
}
