import scala.io.Source
/*
 * this one gets a little harder, we need to examine across all lines
 * in each record and find shared values, we can run a map job that is 
 * very similar to the standard word count, except we are counting chars
 * if we filter that map down to only kv pairs where the v is equal to
 * the number of lines in the record, then the only chars remaining 
 * will be those shared across all lines of the record,
 * we simply calculate this number for each record in the file and sum them
 */

object aoc11{
  def countCommonChars(str:String, lines: Int): Int ={
    return str.map( c => (c, 1)).groupBy(_._1).map{
      case (k,v) => (k, v.map(_._2).sum);// (k,[k,v]) => (k, [v].sum)
    }
      .filter(_._2 == lines).size;
  }
  def main(args: Array[String]){
    var acc = 0; var lines = 0; var str = "";
    Source.fromFile("inputs/day6").getLines.foreach( (line:String) => {
      if (line.isEmpty){
        acc += countCommonChars(str, lines);
        str = ""; lines = 0;
      }else{
        str += line;
        lines += 1;
      }
    })
    acc += countCommonChars(str, lines); //once more for the last record
    println(acc);
  }
}
