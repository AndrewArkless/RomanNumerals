
import org.scalatest.{FlatSpec, FunSpec, Matchers, WordSpecLike, _}
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
//class RomanNumeralsSpec extends FlatSpec with WordSpecLike with Matchers    {
class RomanNumeralsPropertySpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {
  
  implicit override val generatorDrivenConfig =PropertyCheckConfig(minSuccessful=1000)
  val randomNumbers = for (n <- Gen.choose(1, 2000)) yield n

 describe("converting from Arab to Roman") {
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
 }

}