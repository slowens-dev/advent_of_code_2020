import scala.io.Source
import scala.AnyRef

object aoc1 extends App{
  var acc = 0;
  var x = 0;
  Source.fromFile("inputs/day3").getLines.foreach((line:String) => {
    if (line(x) == '#') acc += 1;
    x = (x+3) % line.length
  })
  println(acc);
}
