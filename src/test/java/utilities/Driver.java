package utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.time.Duration;
public class Driver {
    /*
    Driver class'ından driver'i gerDriver() ile kullanıyoruz
    Sonradan projeye katılan insanların driver class'ından obje olusturarak
    driver kullanmaya calışmalarını engellemek için
    Driver class'ını SINGLETON PATERN ile düzenleyebiliriz
    Bunun için Driver class'ının parametresiz Constructer'ını olusturup access modifier'ını PRIVATE yapmamız
    yeterli olur
     */
    private Driver(){
    }
    static  WebDriver driver;
    public static WebDriver getDriver(){
        String browser=ConfigReader.getProperty("browser");
        if (driver==null) {
            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver=new SafariDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
        return driver;
    }
    public static void closeDriver() {
        if (driver!=null)
        {
            driver.close();
            driver=null;
        }
        /*
        1- Driver Class : Oluşturacagımız Framework'de webDriver'i Driver class'ındaki getDriver()
        method'undan alacagız.Driver class'ını biraz daha geliştireceğiz .Farklı browser'larla calısabılır
        hale gelecek
        2- Page Class : Surekli kullandıgımız Locate ıslemlerini ve Logın gibi basit işlevleri yapacak
        method'ları Page class'ında olusturacagız.
        3- Bir Page class'ı olusturur olusturmaz ilk yapmamız gereken sey, parametresiz bir Constructor olusturup,içinde
        PageFactory.initElements(webdriver,this) ile driver'a page sayfasında ilk değer atamasını yapmak olmalıdır.
        4- Assertion : TestNG'de 2 assertion yontemi vardır.
            - Hard Assert : JUnit'deki assert ile aynı method'lara sahiptir . İlk FAILED olan assertion'da
            çalışmayı durdurur ve rapor verir.
            - Soft Assert : Biz raporla diyene kadar assertion'lar FAILED olsa bile çalışmaya devam eder .assertAll() ile
            raporlamasını ıstedıgımızde failed olan  assertion varsa raporlar ve çalışmayı durdurur.
         5- Genelte "test edin" dendiğinde hard assert , doğrulayın(verify) denirse soft assert kullanılır.
         */
    }
}
