import scala.io.Source
import scala.util.control.Breaks.{break, breakable}
/*
 * to do this in any sort of reasonable time we need to do some math tricks
 * the first line can be ignored, 
 * then we zipWithIndex the second line and filter out the "x"s again
 * the zipWithIndex allows us to easily get the offset in the sequence for each bus
 * we initialize our timestamp and the step size as being the first bus ID
 * then we iterate over all the bus IDs except the first one.
 * for each ID we check if the current timestamp+the IDs offset is divisible by the ID
 * if it is not we continue to step up the timestamp by the stepsize until it is
 * when it is we multiply our step size by the current ID
 * this only works because all of our bus IDs are prime and the LCM of a set of primes
 *    is the product of those primes
 */
object aoc25 extends App{
 val source = Source.fromFile("inputs/day13").getLines.toArray;
 val IDs = source(1).split(',').zipWithIndex.filter( _._1 != "x")
  .map( t => (t._1.toInt, t._2) );
 var timestamp, stepSize:BigInt = IDs(0)._1
 for(i <- 1 to IDs.size-1 ){
   while((timestamp+IDs(i)._2) % IDs(i)._1 != 0){
     timestamp += stepSize;
   }
   stepSize *= IDs(i)._1;
 }
 println(timestamp);
 IDs.foreach( lcm *= _._1 );
 println(s"$lcm : ${timestamp/lcm}");
}
