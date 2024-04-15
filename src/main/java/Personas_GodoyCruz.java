import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class Personas_GodoyCruz {
    private WebDriver driver;
    private By usernameLocator = By.id("vUSERNAME");
    private By passwordLocator = By.id("vUSERPASSWORD");
    private By registerBtnLocator = By.name("LOGIN");
    private By menuBtnLocator = By.cssSelector("input[name='BTNTOGGLEMENU_MPAGE']");
    private By tributariofaroBtnLocator = By.xpath("//span[@class='sidebar-nav-item'][contains(.,'Tributario Faro')]");
    private By personasBtn = By.xpath("(//a[contains(.,'Personas')])[1]");
    private By adminPersonasBtn = By.xpath("(//a[contains(.,'Administración de Personas')])[1]");
    private By buscadorPersonas = By.xpath("//input[contains(@id,'vK2BTOOLSGENERICSEARCHFIELD')]");
    private By actualizarBtn = By.xpath("//img[contains(@id,'vUPDATE_0001')]");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        //options.addArguments("--width=1012", "--height=559");
        driver = new FirefoxDriver(options);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        options.addArguments("--headless"); // Configurar el modo headless

        // Crear instancia de WebDriver con las opciones configuradas
        driver = new FirefoxDriver(options);
    }

    @Test
    public void Descarga() throws InterruptedException {
        driver.get("https://gcdigital.godoycruz.gob.ar/K2BGAM/servlet/com.k2bgam.k2blogin");
        Thread.sleep(2000);
        driver.findElement(usernameLocator).sendKeys("dsimoncini");
        driver.findElement(passwordLocator).sendKeys("dsimoncini");
        driver.findElement(registerBtnLocator).click();
        Thread.sleep(7000);
        driver.findElement(menuBtnLocator).click();
        Thread.sleep(2000);
        driver.findElement(tributariofaroBtnLocator).click();
        Thread.sleep(2000);
        driver.findElement(personasBtn).click();
        Thread.sleep(2000);
        driver.findElement(adminPersonasBtn).click();
        Thread.sleep(6000);
        driver.switchTo().frame(0);
        driver.findElement(buscadorPersonas).sendKeys("20263428340", Keys.ENTER);
        Thread.sleep(6000);
        driver.findElement(actualizarBtn).click();
        Thread.sleep(8000);

        // Cambio al nuevo popup
        String popupWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        // Verificar tamaño de la ventana del popup
        int popupWidth = driver.manage().window().getSize().getWidth();
        int popupHeight = driver.manage().window().getSize().getHeight();
        System.out.println("Ancho de la ventana del popup: " + popupWidth + " px");
        System.out.println("Alto de la ventana del popup: " + popupHeight + " px");

        // Comprobación del tamaño de la ventana del popup
        assertTrue(popupWidth == 1382 && popupHeight == 736);

        // Volver al main window
        driver.switchTo().window(popupWindow);

        Thread.sleep(6000);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
