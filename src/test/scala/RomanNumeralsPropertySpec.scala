
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSpec, Matchers}

class RomanNumeralsPropertySpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {

  /* thousand tests of with random numbers upto 2000 to pass before it can be said to have passed
  but what is a reasonable range for this????
  */

  implicit override val generatorDrivenConfig = PropertyCheckConfig(minSuccessful = 1000)

  val randomNumbers = for (n <- Gen.choose(1, 2000)) yield n

  describe("converting from Arab to Roman ") {
    it("Should not contain IIII, XXXX, CCCC") {
      var c=0 // just a counter to demonstrate 1000 numbers are generated
      forAll(randomNumbers) { (num: Int) => {
        val romanNumerals = RomanNumeralConverter.ArabicToRoman(num)
        println(c + " " + num + " " + romanNumerals)
        romanNumerals should not include ("IIII")
        romanNumerals should not include ("XXXX")
        romanNumerals should not include ("CCCC")
        c=c+1
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
     /*
     kata rule states that C can only be subtracted from next 2 highest values which D and  M but then there is no higher value
     so not sure what that means in this context XMM so not implemented
      */


      forAll(randomNumbers) { (num: Int) => {

        val romanNumerals = RomanNumeralConverter.ArabicToRoman(num)

        val illegalSubtractionsOfOne = List("IL", "IC", "ID", "IM")

        illegalSubtractionsOfOne.exists(romanNumerals.contains) shouldBe false

        val illegalSubtractionsOfTen = List("XD", "XM")
        illegalSubtractionsOfTen.exists(romanNumerals.contains) shouldBe false

      }
      }

    }

    it("The '5' symbols ('V', 'L', and 'D') can never be subtracted.") {
      forAll(randomNumbers) { (num: Int) => {

        val romanNumerals = RomanNumeralConverter.ArabicToRoman(num)

        val illegalSubtractionsOfFive= List("VX", "VL", "VC", "VM")
        illegalSubtractionsOfFive.exists(romanNumerals.contains) shouldBe false

        val illegalSubtractionsOfFifty = List("LC", "LD","LM")
        illegalSubtractionsOfFifty.exists(romanNumerals.contains) shouldBe false

        val illegalSubtractionsOfFiveHundred = List("DM")
        illegalSubtractionsOfFiveHundred.exists(romanNumerals.contains) shouldBe false
      }
      }

    }

    /*This test requires the use of a non trivial function to check the property
    this raises the question of how much logic you can put in your tests, without
    having to write tests which test the tests. In this instance tests have been written
    for the RomanNumeralTestHelper in RomanNumerallsTestHelperSpec.
    */

    it("Only one subtraction can be made per numeral .") {
      forAll(randomNumbers) { (num: Int) => {
        val romanNumerals = RomanNumeralConverter.ArabicToRoman(num)
        RomanNumeralTestHelper.oneSubtractionPerNumeral(romanNumerals) shouldBe true}
      }
      }

    it("must meet a regular expression") {
      forAll(randomNumbers) { (num: Int) => {

        /*RegEx lifted from http://bucktownbell.com/?p=3825
          does this need testing to ????
         */
        val numeralRegEx="^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$"

        val romanNumerals = RomanNumeralConverter.ArabicToRoman(num)
        romanNumerals.matches(numeralRegEx) shouldBe true
      }
      }
    }
    }



}