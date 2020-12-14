import scala.io.Source
import scala.util.matching.Regex
import scala.util.control.Breaks.{break, breakable}
/*
 * scala's for loop doesn't allow you to edit the iterator variable so I had
 * to write it out the long way using a while loop and a global iterator 
 * also breaking out of loops requires imports ? 
 *          YUCK!!!!
 * we simply iterate through the lines according to the rules and 
 * we rewrite each line to "exit" after parsing it through the regex
 *  this allows us to detect when  line has already executed 
 *  at that point we will escape execution and print acc
 * 
 */

object aoc15 extends App{
    val lines:Array[String] = Source.fromFile("inputs/day8").getLines.toArray
    val cmdPattern:Regex  = """([a-z]+) ([+-])(\d+)""".r
    var acc = 0; var i = 0;

    breakable(while( i < lines.size ){
      if (lines(i) == "exit") break
      else {
        val cmdPattern(cmd, sign, num) = lines(i);
        lines(i) = "exit";
        cmd match{
          case "acc" => sign match{
            case "+" =>{ acc +=  num.toInt; i += 1; }
            case "-" =>{ acc -=  num.toInt; i += 1; }
          }
          case "jmp" => sign match{
            case "+" => i += num.toInt;
            case "-" => i -= num.toInt;
          }
          case _ =>{ i += 1; }
        }
      }
    })

    println(acc);
}

