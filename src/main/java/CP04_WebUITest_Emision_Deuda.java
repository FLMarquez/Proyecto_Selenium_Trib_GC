import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;




public class CP04_WebUITest_Emision_Deuda {
    public static void main(String[] args) {
        // Setear la propiedad del sistema para el controlador de Firefox (GeckoDriver)
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");

        // Inicializar el navegador Firefox
        WebDriver driver = new FirefoxDriver();

        // Maximizar la ventana del navegador
        driver.manage().window().maximize();

        // Establecer tiempo de espera implícito
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Navegar a la URL deseada
        driver.get("https://gcdigital.godoycruz.gob.ar/K2BGAM/servlet/com.k2bgam.k2blogin");

        // Realizar acciones en la página
        WebElement username = driver.findElement(By.id("&username"));
        username.click();
        username.sendKeys("dsimoncini");

        WebElement userpassword = driver.findElement(By.id("&userpassword"));
        userpassword.click();
        userpassword.sendKeys("dsimoncini");

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();

        // Esperar antes de continuar
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.name("BTNTOGGLEMENU_MPAGE")).click();
        driver.findElement(By.cssSelector("span.sidebar-nav-item")).click();
        driver.findElement(By.linkText("Emisión de Deuda")).click();
        driver.findElement(By.xpath("(//a[contains(.,'Atención Primaria')])[1]")).click();

        // Esperar antes de continuar
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.switchTo().frame(0);
        driver.findElement(By.id("&filtrogeneral")).sendKeys("46860");
        driver.findElement(By.name("buscar")).click();

        // Esperar antes de continuar
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.linkText("Consultar")).click();

        // Esperar antes de continuar
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("GridpersonaContainerTbl")).click();
        driver.findElement(By.id("Tab_TABS_TABSCONTROLContainerpanel1")).click();
        driver.findElement(By.xpath("//a[contains(.,'Seleccionar')]")).click();
        driver.findElement(By.id("vCHECKALL_GRIDOBLIGACIONES")).click();

        // Esperar antes de continuar
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ejecutar script JavaScript para hacer clic en el elemento
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('IMPRIMIRCONTADO').click()");

        // Esperar antes de continuar
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.switchTo().window("win_ser_1");

        driver.findElement(By.linkText("D")).click();
        driver.findElement(By.linkText("Salir")).click();
        driver.quit();
    }
}

