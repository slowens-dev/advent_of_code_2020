import scala.io.Source
import scala.util.control.Breaks.{break, breakable}
/*
 * this one was real pain to figure a clean solution for that wasn't super longwinded
 * first we load our file into an array and hold onto it for cloning
 * then we create an array containing the indexes for every nop or jmp line
 * for each of those nop or jmp lines we ...
 *** clone the array and swap nop to jmp or jmp to nop for the given line
 *** then we define our boolean, incrementer and accumulator, these will reset on each attempt
 *** next we step into the execution and see if the swap will break the infinite loop
 ****** we detect whether the loop is broken by changing the value of a line after it runs
 ****** if we come across a line that has already been run and altered, then that iteration failed
 *** when one of our swaps allow the iteration to succesfully reach the end of the file
 ****** then we know that the swap for that iteration was the correct solution
 *** we print the value of acc and we're done
 */
object aoc15 extends App{
  val source:Array[String] = Source.fromFile("inputs/day8").getLines.toArray
  val jmp_nop_indexes = source.map( _.split(" ")(0) ).zipWithIndex
      .withFilter( t => t._1=="jmp" || t._1=="nop" ).map(_._2);
  for (swapped_i <- jmp_nop_indexes){
    val lines = source.clone()
    lines(swapped_i).split(" ")(0) match{
      case "jmp" => lines(swapped_i) = lines(swapped_i).replace("jmp", "nop")
      case "nop" => lines(swapped_i) = lines(swapped_i).replace("nop", "jmp")
    }
    var acc=0; var i = 0;
    var loop_broken = true;
    breakable (while( i < lines.size ){
      if (lines(i) != "~"){
        val split = lines(i).split(" ");
        lines(i) = "~";
        split(0) match{
          case "acc" => { acc += split(1).toInt; i+=1; }
          case "jmp" => i += split(1).toInt;
          case _ =>{ i += 1; }
        }
      } else {
        loop_broken = false;
        break;
      }
    })
    if ( loop_broken ) {
      println(acc);
      sys.exit(0);
    }
  }
}
