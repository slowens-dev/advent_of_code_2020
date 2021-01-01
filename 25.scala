import scala.io.Source
/*
 * we start by taking the first line and saving it as an Int,
 *  this is our earliest possible departure time.
 * then we remove all "x"s from the second so that we have only bus IDs.
 * next we iterate over those ID's and find the waitTime until that ID's next departure
 * if the waitTime for the ID is the lowest waitTime we've seen,
 *  then we save it's ID and waitTime.
 * the solution is the product of the minimum waitTime and the associated ID
 */
object aoc25 extends App{
 val source = Source.fromFile("inputs/day13").getLines.toArray;
 var minWait, minID = Int.MaxValue;
 val timestamp = source(0).toInt
 val waitTimes = source(1).split(',').filter(_ != "x").foreach((id_s:String) => {
   val id = id_s.toInt;
   val nextTime = ((timestamp/id)+1).toInt * id;
   val waitTime = nextTime - timestamp;
   if(waitTime < minWait){
     minID = id; 
     minWait = waitTime;
   }
 })
 println( minID * minWait );
}
