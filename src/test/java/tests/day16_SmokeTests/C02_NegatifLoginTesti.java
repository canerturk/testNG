package tests.day16_SmokeTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AmazonPage;
import pages.QualitydemyPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
public class C02_NegatifLoginTesti {
    //// 3 test method'u olusturun
    //// 1.de yanlis email, dogru sifre
    //// 2.de dogru email yanlis sifre
    //// 3.de yanlis email, yanlis sifre ile giris yapmayi deneyin
    //// giris yapilamadigini test edin
    QualitydemyPage qualitydemyPage=new QualitydemyPage();
    @Test
    public void yanlisEmailTesti(){
        Driver.getDriver().get(ConfigReader.getProperty("qdUrl"));
        qualitydemyPage.ilkLoginLinki.click();
        qualitydemyPage.kullaniciEmailKutusu.sendKeys(ConfigReader.getProperty("qbGecersizUsername"));
        qualitydemyPage.passwordKutusu.sendKeys(ConfigReader.getProperty("qdGecerliPassword"));
        qualitydemyPage.loginButonu.click();
        Assert.assertTrue(qualitydemyPage.kullaniciEmailKutusu.isDisplayed());
        ReusableMethods.bekle(30);
        Driver.closeDriver();
    }
    @Test
    public void yanlisPassword(){
        qualitydemyPage=new QualitydemyPage();
        Driver.getDriver().get(ConfigReader.getProperty("qdUrl"));
        qualitydemyPage.ilkLoginLinki.click();
        qualitydemyPage.kullaniciEmailKutusu.sendKeys(ConfigReader.getProperty("qbGecerliUsername"));
        qualitydemyPage.passwordKutusu.sendKeys(ConfigReader.getProperty("qdGecersizPassword"));
        qualitydemyPage.loginButonu.click();
        Assert.assertTrue(qualitydemyPage.kullaniciEmailKutusu.isDisplayed());
        ReusableMethods.bekle(30);
        Driver.closeDriver();
    }
    @Test
    public void yanlisUsernameYanlisPasswordTesti(){
        QualitydemyPage qualitydemyPage=new QualitydemyPage();
        Driver.getDriver().get(ConfigReader.getProperty("qdUrl"));
        qualitydemyPage.ilkLoginLinki.click();
        qualitydemyPage.kullaniciEmailKutusu.sendKeys(ConfigReader.getProperty("qbGecersizUsername"));
        qualitydemyPage.passwordKutusu.sendKeys(ConfigReader.getProperty("qdGecersizPassword"));
        qualitydemyPage.loginButonu.click();
        Assert.assertTrue(qualitydemyPage.kullaniciEmailKutusu.isDisplayed());
        ReusableMethods.bekle(30);
        Driver.closeDriver();
    }
}
