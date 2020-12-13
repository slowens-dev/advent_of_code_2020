import scala.io.Source
import scala.AnyRef
/*
 * this one build on aoc03 but isn't really that much more complicated
 * instead of verifying that the number of times a given char occurs 
 * is within a given range, we are verifying that it occurs in one 
 * and only one of the given locations, this is done using XOR
 */

object aoc04 extends App{
  var acc = 0;
  val pattern = """(\d+)-(\d+) (.): (.+)""".r;
  Source.fromFile("inputs/day2").getLines.foreach((line:String) => {
    val pattern(_1, _2, s_c, str) = line;
    val n1=_1.toInt-1; val n2=_2.toInt-1; val c=s_c.head;
    if (str(n1)==c ^ str(n2)==c) acc+=1;
  })
  println(acc);
}
