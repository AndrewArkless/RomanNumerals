import scala.collection.immutable.{ListMap, SortedMap}

/**
  * Created by User on 04/03/2017.
  */
object RomanNumeralConverter {
  val romanNumeralLookup=ListMap(
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
    var result:String=""

    var num1=num
    for((a,r)<-romanNumeralLookup) {
      while(num1>=a ) {
        result=result+r
        num1=num1-a
      }
    }
    result
  }
}
