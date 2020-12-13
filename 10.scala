import scala.io.Source
import scala.collection.mutable.SortedSet
import scala.util.control.Breaks.{breakable, break}

/*
 * for this we can reuse our binary parser, instead of returning the max 
 * we put everything into a sorted set, then look for any number that 
 * get's skipped over by taking each present value and seeing if 
 * that value-1 is present, if it is not then that is our answer
 * we can break after finding the solution, in scala this is 
 * slightly more complicated than normal, but not too bad
 */

object aoc10 extends App{
  val set:SortedSet[Int]= SortedSet.empty
  Source.fromFile("inputs/day5").getLines.foreach( (line:String) => {
    set.add( Integer.parseInt( line.replaceAll("B", "1").replaceAll("F", "0").replaceAll("R", "1").replaceAll("L", "0"), 2) )
  })
  breakable{set.takeRight(set.size-1).foreach( (n:Int) => {
    if (! set(n-1) ){
      println(n-1);
      break;
    }
  })}
}
