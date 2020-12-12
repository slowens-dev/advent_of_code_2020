import scala.io.Source

object aoc1{
  val pattern = "(.+):(.+)".r;

  def isValidPassport(str: String):Boolean = {
    val map = scala.collection.mutable.Map[String, String]();
    str.split(" ").foreach( (pair:String) => {
      val pattern(k, v) = pair;
      map += (k -> v);
    })
    return (map.contains("byr") && map.contains("iyr") &&
            map.contains("eyr") && map.contains("hgt") &&
            map.contains("hcl") && map.contains("ecl") &&
            map.contains("pid"));
  }

  def main(args: Array[String]){
    var acc = 0; var str = "";
    Source.fromFile("inputs/day4").getLines.foreach( (line:String) => {
      if (line.isEmpty){
        if (isValidPassport(str.trim)) acc += 1;
        str = "";
      }else str += " "+line;
    })
    if (isValidPassport(str.trim)) acc += 1;//again for the last line
    println(acc);
  }
}
