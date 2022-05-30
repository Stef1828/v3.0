package view;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

public class pasAanTest {

    WebDriver driver;
    String url = "http://localhost:8080/Webontwikkeling2_war_exploded/VoetballerServlet?command=";

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @After
    public void clean(){
        driver.quit();
    }

    @Test
    public void test_pas_aan_succesvol(){
        driver.findElement(By.id("pasAan0")).click();
        WebElement soortInput = driver.findElement(By.id("competitie"));
        Select select = new Select(soortInput);
        select.selectByValue("Premier league");
        WebElement ratingInput = driver.findElement(By.id("rating"));
        ratingInput.clear();
        ratingInput.sendKeys("9,0");
        driver.findElement(By.id("submit")).click();

        ArrayList<WebElement> lis =
                (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        Assert.assertTrue(containsWebElementsWithText(lis, "Lionel Messi"));
        Assert.assertTrue(containsWebElementsWithText(lis, "Premier League"));
        Assert.assertTrue(containsWebElementsWithText(lis, "9,0"));
    }

    @Test
    public void test_pas_aan_onsuccesvol_rating_leeg(){
        driver.findElement(By.id("pasAan0")).click();
        WebElement soortInput = driver.findElement(By.id("competitie"));
        Select select = new Select(soortInput);
        select.selectByValue("Premier League");
        driver.findElement(By.id("submit")).click();

        ArrayList<WebElement> lis =
                (ArrayList<WebElement>) driver.findElements(By.tagName("p"));
        Assert.assertTrue(containsWebElementsWithText(lis, "rating mag niet lager als 0 of groter als 10 zijn."));
    }

    private boolean containsWebElementsWithText(ArrayList<WebElement> elements, String text) {
        for (WebElement element : elements) {
            if (element.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }
}
