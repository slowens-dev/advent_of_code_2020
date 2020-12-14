import scala.io.Source
import scala.collection.mutable.Map
/*
 * this one made my brain hurt
 * first thing we do is make a map of what bags are inside each bag
 * so we have a master map whos key is a bag 
 * and the value for each key is another map 
 *    the key for the internal map is a bag
 *    the value for the internal map is the number of bags
 *       so if thers a gray bag that holds
 *       2 blue bags and 1 white bag it would be 
 *          "gray" -> ( "blue" -> 2, "white" -> 1 )
 * we start with "shiny gold" and for each bag inside
 * "shiny gold" we add that bags number to the accumulator
 * then we step into that bag and add the number for each
 * bag that is inside of that bag.
 *   the number for a bag inside of a bag is multiplied 
 *   by the number of the holding bag, because each of the 
 *   holding bags of the same type will hold the same number 
 *   of held bags of each type
 * this process recurses so that we count every bag that 
 * is inside of every bag.
 * we subtract 1 from the accumulator at the end because
 * the "shiny gold" bag is counted once in the recursion 
 * and obviously it cannot hold itself.
 * 
 * this comment is probably confusing to read, it was confusing to write
*/

object aoc13{
  val map = Map[String, Map[String, Int]]();
  var acc = 0;

  def countBags(str: String, count: Int){
    map.get(str) match{
      case Some(m) => {
        acc += count;
        m.foreach( (mx) => countBags(mx._1, count * mx._2) )
      }
      case None => {
        acc += count;
      }
    }
  }
  def main(args:Array[String]){
    Source.fromFile("inputs/day7").getLines.foreach( (line:String) => {
      val lineMap = Map[String, Int]();
      val split = line.split(" bags contain ");
      if (split(1) != "no other bags."){
        split(1).replaceAll(" bags", "").replaceAll(" bag", "")
        .dropRight(1).split(", ").foreach( (record:String) => {
          val recordSplit = record.split(" ", 2); 
          lineMap += recordSplit(1) -> recordSplit(0).toInt
        })
      map += split(0) -> lineMap;
      }
    })
    countBags("shiny gold", 1);
    println(acc-1)
  }
}
