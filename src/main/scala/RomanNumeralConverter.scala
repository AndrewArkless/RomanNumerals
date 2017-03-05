import scala.collection.immutable.{ListMap, SortedMap}

/**
  * Created by User on 04/03/2017.
  */
object RomanNumeralConverter {
  val romanNumeralLookup: ListMap[Int, String] =ListMap(
    1000 -> "M",
    900  -> "CM",
    500  -> "D",
    400  -> "CD",
    100  -> "C",
    90   -> "XC",
    50   -> "L",
    40   -> "XL",
    10   -> "X",
    9    -> "IX",
    5    -> "V",
    4    -> "IV",
    1    -> "I"
  )
  def ArabicToRoman(num: Int)={
    def start(tally:Int, numerals:ListMap[Int, String],result:String):String= {
    tally match {
      case 0 => result
      case _ => {
                  val x = helper(tally, numerals, result)
                  start(x._1, x._2, x._3)
               }
  } 
  }
    def helper(tally:Int,n:ListMap[Int,String],result:String):(Int,ListMap[Int,String],String)={

        if (tally<n.head._1) (tally,n.tail,result)
        else {
          helper(tally-n.head._1,n,result+n.head._2)
        }
    }
   start(num,romanNumeralLookup,"")



  }
}
