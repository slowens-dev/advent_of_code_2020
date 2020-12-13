import scala.io.Source
import scala.AnyRef
/*
 * this one is pretty simple, we can reuse our for structure from day 4
 * wherein each record may inhabit more than one line and an empty line
 * marks the delimiter between 2 records.
 * we can concat all lines for a given record into one string then 
 * turn that string into a set which will hold each char at most once
 * then simply sum up the sizes of those sets
 */

object aoc11{
  def main(args: Array[String]){
    var acc = 0; var str = "";
    Source.fromFile("inputs/day6").getLines.foreach( (line:String) => {
      if (line.isEmpty){
        acc += str.toSet[Char].size;
        str = "";
      }else str += line;
    })
    acc += str.toSet[Char].size;//once more for the last record
    println(acc);
  }
}
