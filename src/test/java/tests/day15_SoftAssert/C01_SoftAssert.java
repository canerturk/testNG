package tests.day15_SoftAssert;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AmazonPage;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.Arrays;

public class C01_SoftAssert {
    @Test
    public void test01(){
        //amazon anasayfaya gidin
        Driver.getDriver().get("https://www.amazon.com");

        // amazon anasayfaya gittiğini doğrulayın
        SoftAssert softAssert=new SoftAssert();
        String expectedUrlKelime="amazon";
        String actualUrl=Driver.getDriver().getCurrentUrl();
        softAssert.assertTrue(actualUrl.contains(expectedUrlKelime),"url amazon icermiyor");

        // Nutella aratın
        AmazonPage amazonPage=new AmazonPage();
        amazonPage.amazonAramaKutusu.sendKeys("Nutella"+ Keys.ENTER);

        //arama sonuclarının nutella içerdiğini doğrulayın
        String aramaSonucYaazisi=amazonPage.aramaSonucuElementi.getText();
        softAssert.assertTrue(aramaSonucYaazisi.contains("Nutella"),"arama sonuclari nutella icermiyor");

        //Java aratın
        amazonPage.amazonAramaKutusu.clear();
        amazonPage.amazonAramaKutusu.sendKeys("Java"+Keys.ENTER);

        ReusableMethods.bekle(10);

        //arama sonuclarının 1000'den fazla oldugunu dogrulayın
        System.out.println(amazonPage.aramaSonucuElementi.getText()); //1-48 of over 6,000 results for "Java"

        //1-48 of over 6,000 retuls for Java

        aramaSonucYaazisi=amazonPage.aramaSonucuElementi.getText();

        String[] sonucArr=aramaSonucYaazisi.split(" ");
        System.out.println(Arrays.toString(sonucArr)); // [1-48, of, over, 6,000, results, for, "Java"]

        String javasonucsayisiStr=sonucArr[3];//6,000
        javasonucsayisiStr=javasonucsayisiStr.replaceAll("\\W",""); // 6000

        int sonucSayisiInt=Integer.parseInt(javasonucsayisiStr);

        softAssert.assertTrue(sonucSayisiInt>1000,"java icin arama sayisi 1000 den cok degil");

        softAssert.assertAll(); // softasserti kullandigimizda kapanis icin en alta bunu yazmaliyiz
        Driver.closeDriver();
        // test1
    }
}