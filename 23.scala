import scala.io.Source
import scala.util.matching.Regex
import Math.abs;
/*
 * this one is really straightforward
 * we keep track of the north/south position, decreasing for S and increasing for N
 * we do the same for east/west, decreasing for W and increasing for E
 * we track the angle in a similar fashion
 * when rotating right we increase the angle and take mod 360
 * when rotating left we decrease the angle, if it is negative we add 360
 * taking mod 360 or adding 360 keeps the value in the range of angle degrees (0-359)
 */

object aoc23 extends App{
  var ns, ew = 0;
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
      case "L" => { angle -= n; if(angle < 0) angle += 360; }
      case "R" => angle = (angle + n) % 360;
      case "F" => angle match{
        case 0 => ns += n;
        case 180 => ns -= n;
        case 90 => ew += n;
        case 270 => ew -= n;
      }
    }
  })
  println( abs(ns) + abs(ew) );
}
