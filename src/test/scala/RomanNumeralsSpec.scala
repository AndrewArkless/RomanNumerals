import org.scalatest.{FlatSpec, Matchers, WordSpecLike, _}
import org.scalatest.prop.TableDrivenPropertyChecks._

class RomanNumeralsSpec extends WordSpecLike with Matchers {
  val numerals = Table(
    ("Arabic", "Roman"),
    (1,         "I"),
    (2,         "II"),
    (3,         "III"),
    (4,         "IV"),
    (5,         "V"),
    (6,         "VI"),
    (7,         "VII"),
    (8,         "VIII"),
    (9,         "IX"),
    (10,        "X"),
    (11,        "XI"),//second iteration test 11-20
    (12,        "XII"),
    (13,        "XIII"),
    (14,        "XIV"),
    (15,        "XV"),
    (16,        "XVI"),
    (17,        "XVII"),
    (18,        "XVIII"),
    (19,        "XIX"),
    (20,        "XX"),
    (29,        "XXIX"),
    (40,        "XL"),
    (49,        "XLIX"),
    (50,        "L"),
    (90,        "XC"),
    (99,        "XCIX"),
    (100,       "C"),
    (149,       "CXLIX"),
    (499,       "CDXCIX"),
    (500,       "D"),
    (549,       "DXLIX"),
    (550,       "DL"),
    (900,       "CM"),
    (999,       "CMXCIX"),
    (1000,      "M"),
    (1001,      "MI"),
    (1499,      "MCDXCIX"),
    (1500,      "MD"),
    (1999,      "MCMXCIX"),
    (2000,      "MM")
  )


  "Calling ArabicToRoman" should {
    "return Nulla when 0 is passed" in{
      RomanNumeralConverter.ArabicToRoman(0) shouldBe "Nulla"
    }
    forAll(numerals) { (arabic: Int, roman: String) =>
      s"return $roman when passed $arabic " in {
        RomanNumeralConverter.ArabicToRoman(arabic) shouldBe roman
      }
    }
  }

}