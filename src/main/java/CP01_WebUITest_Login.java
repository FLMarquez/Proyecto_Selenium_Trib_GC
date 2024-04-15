import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import java.io.File;
import java.util.HashMap;


import static org.testng.Assert.assertTrue;

import static org.testng.Assert.assertTrue;


public class CP01_WebUITest_Login {
    private WebDriver driver;
    By usernameLocator = By.id("vUSERNAME");
    By passwordLocator = By.id("vUSERPASSWORD");
    By registerBtnLocator = By.name("LOGIN");
    //By userBtn = By.id("USERNAMEINITIALS_MPAGE");

    //By salirBtn = By.id("SIGNOUT_MPAGE");



    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();

        // Configurar opciones para el controlador de Firefox
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless"); // Configurar el modo headless

        // Crear instancia de WebDriver con las opciones configuradas
        driver = new FirefoxDriver(options);
    }

    @Test
    public void Loguin() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://gcdigital.godoycruz.gob.ar/K2BGAM/servlet/com.k2bgam.k2blogin");
        Thread.sleep(2000);
        driver.findElement(usernameLocator).sendKeys("dsimoncini");
        driver.findElement(passwordLocator).sendKeys("dsimoncini");
        driver.findElement(registerBtnLocator).click();
        Thread.sleep(7000);
        //driver.findElement(userBtn).click();
        //Thread.sleep(2000);
        //driver.findElement(salirBtn).click();
        //Thread.sleep(2000);

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

