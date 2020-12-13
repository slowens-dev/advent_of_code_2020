import scala.io.Source
/*
 * this one gets pretty verbose pretty fast because so many things need
 * to be checked specifically and theres not really a way to generalize
 * it beyond actually checking each requirement
 * we use early false returns as a means of stopping execution at the 
 * first instance of a failure
 */

object aoc08{
  val pairPattern = "(.+):(.+)".r;
  val pidPattern = "[0-9]{9}".r;
  val hclPattern = "#[0-9a-f]{6}".r
  val eyeColors = List("amb", "blu", "brn", "gry", "gry", "grn", "hzl", "oth")

  def isValidPassport(str: String):Boolean = {
    val map = scala.collection.mutable.Map[String, String]();
    str.split(" ").foreach( (pair:String) => {
      val pairPattern(k, v) = pair;
      map += (k -> v);
    })
    map.get("byr") match {
      case Some(s) => if (!( s.toInt >= 1920 && s.toInt <= 2002 ))return false;
      case None => return false;
    }
    map.get("iyr") match {
      case Some(s) => if (!( s.toInt >= 2010 && s.toInt <= 2020 ))return false;
      case None => return false;
    }
    map.get("eyr") match {
      case Some(s) => if (!( s.toInt >= 2020 && s.toInt <= 2030 ))return false;
      case None => return false;
    }
    map.get("hgt") match{
      case Some(s) => {
        s.takeRight(2) match{
          case "cm" => {
            val n = s.take(s.length-2).toInt;
            if(!( n>=150 && n<=193 )) return false;
          }
          case "in" => {
            val n = s.take(s.length-2).toInt;
            if(!( n>=59 && n<=76 )) return false;
          }
          case _ => return false;
        }
      }
      case None => return false;
    }
    map.get("hcl") match{
      case Some(s) => if (!( hclPattern.pattern.matcher(s).matches )) return false;
      case None => return false;
    }
    map.get("ecl") match{
      case Some(s) => if(!( eyeColors.contains(s) )) return false;
      case None => return false;
    }
    map.get("pid") match{
      case Some(s) => if (!( pidPattern.pattern.matcher(s).matches )) return false;
      case None => return false;
    }
    return true;
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
