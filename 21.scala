import scala.io.Source
/*
 * this one has a lot of nested fors and looks a little complicated but its simple
 * for each cycle we first create a 1 cell border all the way around the 2d array
 * this allows us to ignore edge cases where some cells may not have neighbors along
 * the edges of the array. Then we iterate over every cell within that border.
 * for each cell we check all of the neighboring cells and count how many are occupied
 * then we swap occupied cells to empty and empty to occupied occording to the rules
 * as we do this swap we count how many empty cells get swapped to occupied and how
 * many occupied cells remain occupied to get the total occupied cells for that cycle
 * we also count how many cells are swapped,
 * when a cycle results in no swaps we stop cycling
 * the total occupied cells for the cycle which had no swaps is our solution
*/

object aoc21 extends App{
  def cycle(source:Array[String]):(Array[String], Int, Int)= {
    val borderLine = "."*(source(0).size+2)
    val arr = borderLine +: source.map('.'+_+'.') :+ borderLine;
    var outArr = Array.empty[String];
    var changes: Int = 0; var occupied:Int = 0;
    for(y <- 1 to arr.size-2 ){
      var line = "";
      for (x <- 1 to arr(y).size-2 ){
        var neighbors = 0;
        for( dy <- -1 to 1 ){
          for( dx <- -1 to 1 ){
            if ( dy != 0 || dx != 0 ){
              if(arr(y+dy).charAt(x+dx) == '#') neighbors += 1;
            }
          }
        }
        if(arr(y).charAt(x) == 'L'){
          if(neighbors == 0){
            line += '#'
            changes += 1;
            occupied += 1;
          }
          else line += arr(y).charAt(x);
        }else if(arr(y).charAt(x) == '#'){
          if(neighbors >= 4){
            line += 'L'
            changes += 1;
          }
          else {
            line += arr(y).charAt(x);
            occupied += 1;
          }
        }else line += arr(y).charAt(x);
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
