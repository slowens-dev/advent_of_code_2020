import scala.io.Source
import scala.collection.mutable.ArrayBuffer
/*
 * this one is real doozie, had to bust out the recursion
 * first we pass an array holding only "shiny gold" to start, finding all bags that
 * can directly hold a shiny gold bag and putting them in an array
 *  we add the size of that array to our accumulator
 *  we remove the line for each bag we accumulate from the master 
 *    set of lines so we don't recount the same bag twice
 * then we take the array of bags we've built and pass it back into
 * the function to find any bag that could hold one of the bags in the array
 * the function tail recurses until there are no longer any bags that 
 * could potentially hold a bag in our current list of bags,
 * then print the accumulator and exit
*/

object aoc13{
    val lines:ArrayBuffer[String] = Source.fromFile("inputs/day7").getLines.toArray.to[ArrayBuffer]
    var acc = 0;

    def bagsContain(bags:Array[String]){
        val bag_buff: ArrayBuffer[String] = ArrayBuffer()
        val drop_buff: ArrayBuffer[String] = ArrayBuffer()
        lines.foreach((line:String) => {
            val split = line.split("bags contain");
            if (bags.exists( split(1).contains(_) )){
                bag_buff += split(0);
                drop_buff += line;
            }
        })
        if (bag_buff.nonEmpty){
            lines --= drop_buff
            acc += bag_buff.size;
            bagsContain(bag_buff.toArray)
        }else
            println(acc)
    }
    def main(args:Array[String]){
        bagsContain( Array("shiny gold") )
    }
}
