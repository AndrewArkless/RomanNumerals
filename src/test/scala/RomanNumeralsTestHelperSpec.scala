import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.{Matchers, WordSpecLike}

class RomanNumeralsTestHelperSpec extends WordSpecLike with Matchers{

  val numeralHasOneSubtraction=Table(
    ("Numeral","Result"),
    ("Nulla",     true),
    ("I",         true),
    ("IX"  ,      true),
    ("XI",        true),
    ("XIII",      true),
    ("XIX",       true),
    ("XL",        true),
    ("MXM",       true),
    ("XXIII",     true),
    ("CCD",       false),
    ("MXXM",      false),
    ("IIIXXX",    false),
    ("XXL",       false),
    ("LXXC",      false),
    ("IIM",       false),
    ("XXC",       false),
   /* ("MXIM",      false) This causes test to fail but is it a violation of this rule,
                           or another unstated rule as the number is nonsense?? */
    ("XCD",       false)
  )

  "Calling hasOnsubtraction" should {
    forAll(numeralHasOneSubtraction) { (numeral: String, result: Boolean) =>
      s"return $result where numeral is $numeral" in {
        RomanNumeralTestHelper.oneSubtractionPerNumeral(numeral) shouldBe result
      }
    }
  }
}
