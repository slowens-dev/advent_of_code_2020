import scala.io.Source;
/*
 * this one is logically pretty straight forward, just had to write my own parser to go 
 * to and from 36 bit binary integers.
 * we don't need to cast to an array for this because we can look at each line in order,
 * so we can just call foreach and step into it.
 * if the line is a mask line we will update our mask,
 * otherwise we will parse the given int into a 36 bit binary, then apply the mask.
 * after applying the mask we parse it back into a BigInt and save it in the map under
 * the given "mem".
 * at the end we simply sum up all of the values of the map into a BigInt and print it
*/
object aoc extends App{
  var mask = "";
  var map = Map[Int, BigInt]();
  Source.fromFile("inputs/day14").getLines.foreach((line:String) => {
    val line_arr = line.split(" = ");
    if (line_arr(0) == "mask"){
      mask = line_arr(1);
    }else{
      val mem:Int = line_arr(0).drop(4).dropRight(1).toInt;
      val num:BigInt = BigInt(line_arr(1).toInt)
      val num36:String = to36bit(num);
      val masked36:String = applyMask(num36, mask);
      val masked:BigInt = from36bit(masked36);
      map += mem -> masked;
    }
  })
  var out:BigInt = 0;
  map.values.foreach((v:BigInt) => out = out + v );
  println(out);
  //functions
  def to36bit(n:BigInt):String = {
    var out:String = "";
    var nn:BigInt = n;
    for(i <- 35 to 0 by -1){
      val pow_n = 1L << i;
      if( nn >= pow_n ){
        nn = nn - pow_n;
        out += "1";
      }else out += "0";
    }
    return out;
  }
  def from36bit(s:String):BigInt = {
    var out:BigInt = 0;
    val sr = s.reverse;
    for( i <- 0 to 35 ){
      if(sr.charAt(i) == '1'){
        val pow_n:BigInt = 1L << i;
        out = out + pow_n;
      }
    }
    return out;
  }
  def applyMask(bit36:String, mask:String):String = {
    var out:String = "";
    for(i <- 0 to 35 ){
      mask.charAt(i) match{
        case '1' => out += '1';
        case '0' => out += '0';
        case 'X' => out += bit36.charAt(i);
      }
    }
    return out;
  }

}
