
import org.scalatest.{FlatSpec, FunSpec, Matchers, WordSpecLike, _}
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
//class RomanNumeralsSpec extends FlatSpec with WordSpecLike with Matchers    {
class RomanNumeralsPropertySpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {

  implicit override val generatorDrivenConfig = PropertyCheckConfig(minSuccessful = 1000)
  val randomNumbers = for (n <- Gen.choose(1, 2000)) yield n

  describe("converting from Arab to Roman ") {
    it("Should not contain IIII, XXXX, CCCC") {
      forAll(randomNumbers) { (num: Int) => {
        val romanNumerals = RomanNumeralConverter.ArabicToRoman(num)
        println("HERE" + num + romanNumerals)
        romanNumerals should not include ("IIII")
        romanNumerals should not include ("XXXX")
        romanNumerals should not include ("CCCC")
      }
      }

    }

    it("Should not contain more than one V,L or D") {
      forAll(randomNumbers) { (num: Int) => {

        val romanNumerals = RomanNumeralConverter.ArabicToRoman(num)
        romanNumerals.count(_ == 'V') should be < 2
        romanNumerals.count(_ == 'L') should be < 2
        romanNumerals.count(_ == 'D') should be < 2


      }
      }
    }
    it("The '1' symbols ('I', 'X', and 'C') can only be subtracted from the 2 next highest values ('IV' and 'IX', 'XL' and 'XC', 'CD' and 'CM')") {
      forAll(randomNumbers) { (num: Int) => {

        val romanNumerals = RomanNumeralConverter.ArabicToRoman(num)

        val illegalSubtractionsOfOne = List("IL", "IC", "ID", "IM")

        illegalSubtractionsOfOne.exists(romanNumerals.contains) shouldBe false

        val illegalSubtractionsOfTen = List("XD", "XM")
        illegalSubtractionsOfTen.exists(romanNumerals.contains) shouldBe false

      }
      }

    }

  }
}