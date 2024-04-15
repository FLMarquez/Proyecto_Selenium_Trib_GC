import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

import java.io.File;
//import java.util.HashMap;

import static org.testng.Assert.assertTrue;

class TestNGFirefox {
    private WebDriver driver;
    private String downloadFilePath = "C:\\Users\\Lmarquez\\Desktop\\Escritorio PC Lucas\\GODOY CRUZ\\DescargaPDF";
    By usernameLocator = By.id("vUSERNAME");
    By passwordLocator = By.id("vUSERPASSWORD");
    By registerBtnLocator = By.name("LOGIN");
    By menuBtnLocator = By.name("BTNTOGGLEMENU_MPAGE");
    By tributariofaroBtnLocator = By.xpath("//span[@class='sidebar-nav-item'][contains(.,'Tributario Faro')]");
    By emisionDeuda = By.xpath("(//a[contains(.,'Emisión de Deuda')])[1]");
    By atencionPrimaria = By.xpath("(//a[contains(.,'Atención Primaria')])[1]");
    By filtroObjeto = By.name("vFILTROGENERAL");
    By buscarBtn = By.id("BUSCAR");
    By consultarBtn = By.xpath("(//a[@class='ui right floated big purple label'][contains(.,'Consultar')])[1]");
    By tasasObjetos = By.xpath("//a[contains(@id,'TABSCONTROLContainerpanel1')][@href='#Tab_TabControl'][contains(.,'TASAS/SERVICIOS/OBJETOS')]");
    //By checkObligaciones = By.xpath("//label[@for='vCHECKALL_GRIDOBLIGACIONES'][contains(.,'_')]");
    By selectBtn = By.id("span_vACTION1_ACTION_0001");

    //By descargaBtnid = By.id("download");

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
        driver.get("https://gcdigital.godoycruz.gob.ar/K2BGAM/servlet/com.k2bgam.k2blogin");
        Thread.sleep(2000);
        driver.findElement(usernameLocator).sendKeys("dsimoncini");
        driver.findElement(passwordLocator).sendKeys("dsimoncini");
        driver.findElement(registerBtnLocator).click();
        Thread.sleep(9000);
        driver.findElement(menuBtnLocator).click();
        Thread.sleep(2000);
        driver.findElement(tributariofaroBtnLocator).click();
        Thread.sleep(2000);
        driver.findElement(emisionDeuda).click();
        Thread.sleep(2000);
        driver.findElement(atencionPrimaria).click();
        Thread.sleep(6000);
        driver.switchTo().frame(0);
        driver.findElement(filtroObjeto).sendKeys("46860");
        Thread.sleep(2000);
        driver.findElement(buscarBtn).click();
        Thread.sleep(2000);
        driver.findElement(consultarBtn).click();
        Thread.sleep(8000);
        driver.findElement(tasasObjetos).click();
        Thread.sleep(7000);
        driver.findElement(selectBtn).click();
        Thread.sleep(6000);
        WebElement imprimirBtnContado = driver.findElement(By.id("IMPRIMIRCONTADO"));
        imprimirBtnContado.click();
        Thread.sleep(6000);
        WebElement dBtn = driver.findElement(By.xpath("//a[@href='#'][contains(.,'D')]"));
        dBtn.click();
        Thread.sleep(6000);
        //WebElement salirBtn = driver.findElement(By.xpath("//a[@href='#'][contains(.,'Salir')]"));
        //salirBtn.click();


        //String mainWindow = driver.getWindowHandle();
        //for(String windowHandle : driver.getWindowHandles()) {
        //driver.switchTo().window(windowHandle);
        //}
        //Thread.sleep(16000);
        // Realizar acciones en la ventana emergente, como encontrar y hacer clic en el botón de descarga
        //driver.findElement(descargaBtnid).click();


        //Thread.sleep(16000);
        // Volver al contexto principal
        //driver.switchTo().window(mainWindow);

        // Verificar la descarga de archivos
        File folder = new File(downloadFilePath);
        File[] listOfFiles = folder.listFiles();
        assertTrue(listOfFiles != null && listOfFiles.length > 0, "No se descargaron archivos correctamente");

        // Renombrar el archivo descargado
        //File downloadedFile = listOfFiles[listOfFiles.length - 1];
        //String newFileName = "prueba.pdf"; // Establece el nombre que desees
        //File renamedFile = new File(downloadFilePath + File.separator + newFileName);
        //downloadedFile.renameTo(renamedFile);

        // Verificar si el archivo se ha descargado correctamente con el nuevo nombre
        //assertTrue(renamedFile.exists(), "No se pudo cambiar el nombre del archivo descargado");

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
