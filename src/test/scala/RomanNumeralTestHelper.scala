object RomanNumeralTestHelper {
  val numerals= Map(
    "M"->1000,
    "D"->500,
    "C"->100,
    "L"->50,
    "X"->10,
    "V"->5,
    "I"->1
  )

  def oneSubtractionPerNumeral(numeral: String)={
    def helper(n: List[Int], result:Boolean):Boolean= {
      (n,result) match {
        case (Nil,_) => true
        case (t::Nil,_)=>true
        case (_,true) if n.head < n.tail.head => false
        case _ => helper(n.tail,n.head <= n.tail.head)
      }
    }
    def convertNumeraltoArabic(numeral:String) ={
      {
        for (num<-numeral) yield{
          numerals(num.toString)
        }
      }.toList

    }
    if (numeral=="Nulla") true
    else helper(convertNumeraltoArabic(numeral),false)
  }
}
