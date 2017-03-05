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
                  val x = helper(tally, numerals.head._1,numerals.head._2, result)
                  start(x._1, numerals.tail,x._2)
               }
          }
    }

    def helper(tally:Int,arabNumber:Int,romanNumber:String,result:String):(Int,String)={
        if (tally<arabNumber) (tally,result)
        else {
          helper(tally-arabNumber,arabNumber,romanNumber,result+romanNumber)
        }
    }
   start(num,romanNumeralLookup,"")



  }
}
