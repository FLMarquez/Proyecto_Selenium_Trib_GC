import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import java.util.concurrent.TimeUnit;
import java.io.File;
import static org.testng.Assert.assertTrue;

public class CP02_WebUITest_Personas1 {
    private WebDriver driver;
    private String downloadFilePath = "C:\\Users\\Lmarquez\\Desktop\\Escritorio PC Lucas\\GODOY CRUZ\\DescargaPDF";
    By usernameLocator = By.id("vUSERNAME");
    By passwordLocator = By.id("vUSERPASSWORD");
    By registerBtnLocator = By.name("LOGIN");
    By menuBtnLocator = By.name("BTNTOGGLEMENU_MPAGE");
    By tributariofaroBtnLocator = By.xpath("//span[@class='sidebar-nav-item'][contains(.,'Tributario Faro')]");
    By personasLink = By.xpath("(//a[contains(.,'Personas')])[1]");
    By administracionPersonasLink = By.xpath("//a[@href='com.k2bgam.paginaembedded?49,mdlsgt.wwpersonas,'][contains(.,'Administración de Personas')]");
    By searchFieldLocator = By.id("vK2BTOOLSGENERICSEARCHFIELD");
    By submitButtonLocator = By.id("MAINFORM");
    By verButtonLocator = By.id("vVER_0001");
    By tab1Locator = By.id("Tab_TABS_TABSCONTROLContainerpanel1");
    By tab2Locator = By.id("Tab_TABS_TABSCONTROLContainerpanel2");
    By tab3Locator = By.id("Tab_TABS_TABSCONTROLContainerpanel3");
    By tab4Locator = By.id("Tab_TABS_TABSCONTROLContainerpanel4");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");

        try {
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            firefoxProfile.setPreference("browser.download.folderList", 2);
            firefoxProfile.setPreference("browser.download.dir", downloadFilePath);
            firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
            firefoxProfile.setPreference("pdfjs.disabled", true);
            firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);

            FirefoxOptions options = new FirefoxOptions();
            options.setProfile(firefoxProfile);
            //options.addArguments("--headless"); // Agregar esta línea para modo headless

            driver = new FirefoxDriver(options);
        } catch (Exception e) {
            System.out.println("Error al configurar el perfil de Firefox: " + e.getMessage());
        }
    }

    @Test
    public void Descarga() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://gcdigital.godoycruz.gob.ar/K2BGAM/servlet/com.k2bgam.inicio?26ebae9e-85a8-4815-b4e0-c4d2266263ec%21a36db5951833972840b3ca5f87dcf34a9166b25652eadda1428b3f32e2d5894263a28e3d655533");
        Thread.sleep(2000);
        driver.findElement(usernameLocator).sendKeys("dsimoncini");
        driver.findElement(passwordLocator).sendKeys("dsimoncini");
        driver.findElement(registerBtnLocator).click();
        Thread.sleep(9000);
        driver.findElement(menuBtnLocator).click();
        Thread.sleep(2000);
        driver.findElement(tributariofaroBtnLocator).click();
        Thread.sleep(2000);
        driver.findElement(personasLink).click();
        Thread.sleep(2000);
        driver.findElement(administracionPersonasLink).click();
        Thread.sleep(6000);
        driver.switchTo().frame(0);
        driver.findElement(searchFieldLocator).sendKeys("20263428340");
        driver.findElement(submitButtonLocator).submit();
        Thread.sleep(6000);
        driver.findElement(verButtonLocator).click();
        Thread.sleep(9000);
        driver.findElement(tab1Locator).click();
        Thread.sleep(2000);
        driver.findElement(tab2Locator).click();
        Thread.sleep(2000);
        driver.findElement(tab3Locator).click();
        Thread.sleep(2000);
        driver.findElement(tab4Locator).click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}