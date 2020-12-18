import scala.io.Source
/*
 * this one is pretty straight forward
 * we start at line 25 as our checking int
 * next we sum up every combination of the 25 previous ints
 * if none of those combos match our checking int then that is our solution
*/

object aoc17 extends App{
  val source = Source.fromFile("inputs/day9").getLines.map(BigInt(_)).toArray
  for(i <- 25 to source.size-1){
    var check = false;
    val ni = source(i)
    for(j <- i-25 to i-1){
      val nj = source(j)
      for(k <- j+1 to i-1){
        val sum = nj + source(k)
        if (sum == ni) check = true;
      }
    }
    if (!check){
      println(ni);
      sys.exit(0);
    }
  }
}
