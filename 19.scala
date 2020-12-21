import scala.io.Source
import scala.collection.mutable.Map
/*
 * start by sorting the list so we can simply iterate over it once
 * and check the difference between consecutive values.
 * we use a map to track the number of times each difference appears.
 * the map is initialized with 3->1 bc our built in adapter is 3 jolts
 * higher than the highest in the list.
 * we add the lowest value in the list to the map (or increment 3 if it is 3)
 * to show the difference between this and 0.
 * then we simply iterate over everything but the first and add to the map or increment 
 * the difference between it and the previous value 
 */

object aoc19 extends App{
  val source = Source.fromFile("inputs/day10").getLines.map(_.toInt).toArray.sorted;
  val map = Map[Int, Int]( 3 -> 1);
  map.get( source(0) ) match{
    case Some(v) => map += source(0) -> (v+1);
    case None => map += source(0) -> 1;
  }
  for(i <- 1 to source.size-1){
    val k = source(i) - source(i-1);
    map.get( k ) match{
      case Some(v) => map += k -> (v+1)
      case None => map += k -> 1
    }
  }
  map.get(1) match{
    case Some(v1) => map.get(3) match{
      case Some(v2) => println(v1 * v2);
      case None => {}
    }
    case None => {}
  }
}
