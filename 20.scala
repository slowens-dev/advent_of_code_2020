import scala.io.Source
import scala.collection.mutable.Map
/*
 * we will first sort our values in ascending order
 * we will then prepend 0 and append the highest value + 3
 * next we step through the list recursively
 *  if the value is the final element in the list we return 1
 *    there is only one possible path from the final element, ending
 *  next we will check our map of paths to see if the current value has 
 *  already had it's number of possible paths computed
 *    if it has we return the value that has already been saved and dont recompute
 *    if it has not we compute the number of paths from that value
 *     to do this we initialize a variable to 0 as our counter
 *     then we check the next 3 values, and recursively all possible paths from them
 *     and add their number of possible paths to the total.
 *    after we compute the number of possible paths from a given element
 *      we add that element and its number of paths to our map
 *      the we return it, either adding to the total of the recursive caller
 *      or breaking out and returning the final solution
 */

object aoc extends App{
  def pathCount(arr:Array[Int], i:Int=0, paths:Map[Int, BigInt]=Map.empty[Int, BigInt])
  : BigInt = {
    if ( i == arr.size-1 ) return 1;
    paths.get(i) match{
      case Some(v) => return v;
      case None => {
        var total:BigInt = 0;
        if ((i+1 < arr.size) && (arr(i+1) - arr(i) <= 3)){
          total += pathCount(arr, i+1, paths)
          if ((i+2 < arr.size) && (arr(i+2) - arr(i) <= 3)){
            total += pathCount(arr, i+2, paths)
            if ((i+3 < arr.size) && (arr(i+3) - arr(i) <= 3)){
              total += pathCount(arr, i+3, paths)
            }
          }
        }
        paths += i -> total;
        return total;
      }
    }
  }

  val source = Source.fromFile("./inputs/day10").getLines.map(_.toInt).toArray.sorted
  val arr = 0 +: source :+ source(source.size-1)+3;
  println( pathCount(arr) );
}
