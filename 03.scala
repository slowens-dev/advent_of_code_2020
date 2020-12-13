import scala.io.Source
import scala.AnyRef
/*
 * split the data up with a regex use it to get the range of 
 * the number of times the given char may appear
 * verify that it neither appears more or less than specified
 */

object aoc03 extends App{
  var acc = 0;
  val pattern = """(\d+)-(\d+) (.): (.+)""".r;
  Source.fromFile("inputs/day2").getLines.foreach((line:String) => {
    val pattern(_1, _2, c, str) = line;
    val count = line.count(_ == c.head);
    if ( count >= _1.toInt+1 && count <= _2.toInt+1 ) acc += 1;
  })
  println(acc);
}
