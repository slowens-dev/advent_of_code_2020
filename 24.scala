import scala.io.Source
import scala.util.matching.Regex
import Math.abs;
/*
 * this one inherits a lot of the boilerplate from day 12 part 1 but has some
 * significant differences, instead of simply turning the boat n degrees left or right
 * we now have to rotate the waypoint about the boat n degrees.
 * we can assume the rotations only come as multiples of 90 degrees
 * this makes it as easy as swapping the values and/or taking their negative
 * if we were accepting any degree we would actually have to do some calculation
 * in addition the forward method now simply moves to the waypoint n times
 * for this we track boat position as well as waypoint position
 * then we simply multiply the changes from moving to the waypoint by n
 * 
 */

object aoc24 extends App{
  var ew_p, ns_p = 0;
  var ew = 10;
  var ns = 1;
  var angle = 90;
  val pattern:Regex = """([NSEWLRF])(\d+)""".r
  Source.fromFile("inputs/day12").getLines.foreach((line:String) => {
    val pattern(op, n_string) = line;
    val n = n_string.toInt
    op match{
      case "N" => ns += n;
      case "S" => ns -= n;
      case "E" => ew += n;
      case "W" => ew -= n;
      case "L" => {
        val t = rotate_waypoint(ew, ns, 360-n);
        ew = t._1;
        ns = t._2;
      }
      case "R" => {
        val t = rotate_waypoint(ew, ns, n);
        ew = t._1;
        ns = t._2;
      }
      case "F" => {
        ew_p += (n * ew);
        ns_p += (n * ns);
      }
    }
  })
  println( abs(ns_p) + abs(ew_p) );
  def rotate_waypoint(ew:Int, ns:Int, angle:Int):(Int, Int)={
    angle match{
      case 90 => return (ns, -ew);
      case 180 => return (-ew, -ns);
      case 270 => return (-ns, ew);
    }
  }
}
