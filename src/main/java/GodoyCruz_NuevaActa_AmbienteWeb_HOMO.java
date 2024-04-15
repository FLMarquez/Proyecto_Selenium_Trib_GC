import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertTrue;

class GodoyCruz_NuevaActa_AmbienteWeb_HOMO {
    private WebDriver driver;
    private String downloadFilePath = "C:\\Users\\Lmarquez\\Desktop\\Escritorio PC Lucas\\GODOY CRUZ\\DescargaPDF\\Nuevacta\\HOMOLOGACION";
    By usernameLocator = By.id("vUSERNAME");
    By passwordLocator = By.id("vUSERPASSWORD");
    By registerBtnLocator = By.name("LOGIN");
    By menuBtnLocator = By.name("BTNTOGGLEMENU_MPAGE");
    By nuevacta = By.linkText("Nuevas Actas");
    By consultaActa = By.linkText("Consulta de Actas");

    By estadoActa = By.id("span_VW_ACT_ACBESTADO_0001");

    //By filtroActa = By.cssSelector("#LAYOUTDEFINED_FILTERTOGGLE_COMBINED_GRIDACTATRANSITO");
    //By tipoActa = By.name("vTPAID_FILTRO");
    //By closeTipoacta = By.id("LAYOUTDEFINED_FILTERCLOSE_COMBINED_GRIDACTATRANSITO");
    //By imprimirWeb = By.id("vIMPRIMIRWEB_ACTION_0001");

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
            options.addArguments("--headless"); // Agregar esta línea para modo headless


            driver = new FirefoxDriver(options);
        } catch (Exception e) {
            System.out.println("Error al configurar el perfil de Firefox: " + e.getMessage());
        }
    }


    @Test
    public void Descarga() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://gcdigitalhomo.godoycruz.gob.ar/K2BGAM/servlet/com.k2bgam.k2blogin");
        //driver.manage().window().setSize(new Dimension(800, 758));
        Thread.sleep(2000);
        driver.findElement(usernameLocator).sendKeys("dsimoncini");
        driver.findElement(passwordLocator).sendKeys("dsimoncini456");
        WebElement tipoAmbiente = driver.findElement(By.id("vAPPENVNAME"));
        Thread.sleep(4000);
        Select tipoAmbienteSelect = new Select(tipoAmbiente);
        tipoAmbienteSelect.selectByVisibleText("K2B Homologación");
        driver.findElement(registerBtnLocator).click();
        Thread.sleep(8000);
        driver.findElement(menuBtnLocator).click();
        Thread.sleep(6000);
        // Crear una instancia de JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Desplazarse hasta el final de la página usando JavaScript
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(6000);
        driver.findElement(nuevacta).click();
        Thread.sleep(5000);
        driver.findElement(consultaActa).click();
        Thread.sleep(8000);
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("#LAYOUTDEFINED_FILTERTOGGLE_COMBINED_GRIDACTATRANSITO")).click();
        Thread.sleep(8000);
        //driver.switchTo().frame(0);
        WebElement tipoActa = driver.findElement(By.id("vTPAID_FILTRO"));
        Select tipoActaSelect = new Select(tipoActa);
        tipoActaSelect.selectByVisibleText("AMBIENTE");
        Thread.sleep(6000);
        driver.findElement(By.id("LAYOUTDEFINED_FILTERCLOSE_COMBINED_GRIDACTATRANSITO")).click();
        Thread.sleep(8000);
        //driver.findElement(By.cssSelector("#vIMPRIMIRWEB_ACTION_0003")).click();
        // Encontrar el elemento (por ejemplo, un botón) usando XPath
        WebElement element = driver.findElement(By.cssSelector("#vIMPRIMIRWEB_ACTION_0001"));
        Thread.sleep(6000);
        // Desplazarse hacia el elemento usando JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(8000);
        driver.findElement(estadoActa).click();
        Thread.sleep(2000);
        element.click();
        //driver.findElement(By.id("gxp0_gxtitle")).click();
        //driver.findElement(By.id("gxp0_cls")).click();
        Thread.sleep(8000);


        // Verificar la descarga de archivos
        File folder = new File(downloadFilePath);
        File[] listOfFiles = folder.listFiles();
        assertTrue(listOfFiles != null && listOfFiles.length > 0, "No se descargaron archivos correctamente");

        //Renombrar el archivo descargado
        File downloadedFile = listOfFiles[listOfFiles.length - 1];
        String newFileName = "Acta_AmbienteWeb.pdf"; // Establece el nombre que desees
        File renamedFile = new File(downloadFilePath + File.separator + newFileName);
        downloadedFile.renameTo(renamedFile);

        // Verificar si el archivo se ha descargado correctamente con el nuevo nombre
        assertTrue(renamedFile.exists(), "No se pudo cambiar el nombre del archivo descargado");

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}


