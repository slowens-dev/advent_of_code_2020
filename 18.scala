import scala.io.Source
import scala.util.control.Breaks.{break, breakable}
/*
 * again this one's pretty easy
 * we rerun aoc17 so the solution remains programmatic
 * then we step through list repeatedly looking at contiguous values
 * if a stream of contiguous values is >= the key we break the stream
 * if after the stream is finalized the sum is equal to the key then
 * that is our solution, otherwise start the next stream one value
 * deeper into the list
 * for each stream we track the highest and lowest value to compute the final answer
 *
 * a single loop solution could be done by tracking the first and last index in the stream
 * if the accumulator becomes greater than the key we can subtract the value of the first
 * index then shift that first index up one to the next value. 
 * when the accumulator is still less than the key we can add the value of the last index
 * if the accumulator is equal to the key we have our solution
 * but I'm sleepy and this was faster to write.
 */

object aoc18 extends App{
  def findKey(source: Array[BigInt]):BigInt = {
    var ret:BigInt = 0;
    breakable( for(i <- 25 to source.size-1){
      var check = false;
      val ni = source(i);
      for(j <- i-25 to i-1){
        val nj = source(j);
        for(k <- j+1 to i-1){
          val sum = nj + source(k);
          if (sum == ni) check = true;
        }
      }
      if (!check) ret = ni;
    } )
    return ret;
  }
  val source = Source.fromFile("inputs/day9").getLines.map(BigInt(_)).toArray;
  val key:BigInt = findKey(source);
  breakable (for( i <- 0 to source.size-1 ){
    var acc:BigInt=0; var low:BigInt=source(i); var high:BigInt=(i)
    breakable (for( j <- i to source.size-1 ){
      acc += source(j);
      if (source(j) > high) high = source(j);
      else if (source(j) < low) low = source(j);
      if (acc >= key) break;
    } )
    if (acc == key){
      println(high + low);
      break;
    }
  }) 
}
