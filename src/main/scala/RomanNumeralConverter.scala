import scala.collection.immutable.ListMap

/**
  * Created by User on 04/03/2017.
  */
object RomanNumeralConverter {
  val Nulla="Nulla"
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

     def start(remainingValue:Int, numerals:ListMap[Int, String],result:String):String= {
       remainingValue match {
         case 0 => result
         case _ => {
           val arabicValue = numerals.head._1
           val romanValue = numerals.head._2
           if (remainingValue < arabicValue) start(remainingValue, numerals.tail, result)
           else {
             start(remainingValue  % arabicValue, numerals.tail, result + (romanValue * (remainingValue / arabicValue)))
           }
         }
       }
     }
       if (num<=0) Nulla
       else start(num, romanNumeralLookup, "")

     }
  }

