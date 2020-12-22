import scala.io.Source
/*
 * this one is pretty similar to day11 part1.
 * instead of only looking at the bordering cells we look outward in every direction
 *  (left, right, up, down, up+left, up+right, down+left, down+right).
 * for each direction we continue to look farther and farther out until we see a seat.
 * if that seat is occupied we incremnt neighbors, if it is empty we dont.
 * after looking in all 8 directions we will have somewhere from 0 to 8 neighbors.
 * then we make our swaps and increment our occupied and changes totals just the same
 *  as we did in part1, with the only difference being we look for 5 or more neighbors
 *  before making a seat empty as opposed to 4 or more in part 1
 * 
 * we dont use the bordering trick to get around edge cases because it it could 
 *  not work in this scenario.
 * here we have to look outward further and further until we see a seat and it's 
 *  possible we never see one so we have to consider the possibility of stepping 
 *  outside of the array indexes. 
 * so here we do a 4 part if statement to verify all indexes are in range
*/

object aoc22 extends App{
  def cycle(source:Array[String]):(Array[String], Int, Int)= {
    var outArr = Array.empty[String];
    var changes: Int = 0; var occupied:Int = 0;

    def countNeighbors(y:Int, x:Int, ddy:Int, ddx:Int):Int={
      var loop = true;
      var dy = 0; var dx = 0;
      while(loop){
        dy += ddy; dx += ddx;
        if(y+dy == -1 || y+dy == source.size || 
           x+dx == -1 || x+dx == source(y).size ||
           source(y+dy).charAt(x+dx) == 'L') loop = false;
        else if( source(y+dy).charAt(x+dx) == '#') return 1;
      }
      return 0;
    }

    for(y <- 0 to source.size-1 ){
      var line = "";
      for (x <- 0 to source(y).size-1 ){
        val c = source(y).charAt(x);
        if(c == '.') line += c;
        else{
          var neighbors = 0;
          for(ddy <- -1 to 1) for(ddx <- -1 to 1)
              if (ddx != 0 || ddy != 0) neighbors += countNeighbors(y,x, ddy, ddx);
          if(c == 'L'){
            if (neighbors == 0){
              line += '#';
              occupied += 1;
              changes += 1;
            }
            else line += 'L';
          }
          else if(c=='#'){
            if (neighbors >= 5){
              line += 'L';
              changes += 1;
            }
            else{
              line += '#';
              occupied += 1;
            }
          }
        }
      }
      outArr = outArr :+ line;
    }
    return (outArr, changes, occupied);
  }
  var tup = ( Source.fromFile("inputs/day11").getLines.toArray , -1, 0 );
  while( tup._2 != 0){
    tup = cycle(tup._1);
  }
  println(tup._3);
}
