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

import java.util.ArrayList;

public class TestAddVoetballer {

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
    private boolean containsWebElementsWithText(ArrayList<WebElement> elements, String text) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().equals(text)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test_Form_is_shown_again_with_error_messages_If_voornaam_naam_field_is_left_empty() {
        WebElement voornaam_naamInput = driver.findElement(By.id("voornaam_naam"));
        voornaam_naamInput.clear();
        voornaam_naamInput.sendKeys("");

        WebElement competitieInput = driver.findElement(By.id("competitie"));
        competitieInput.clear();
        competitieInput.sendKeys("Premier League");

        WebElement clubInput = driver.findElement(By.id("club"));
        clubInput.clear();
        clubInput.sendKeys("Manchester City");

        WebElement nummerInput = driver.findElement(By.id("nummer"));
        nummerInput.clear();
        nummerInput.sendKeys("17");

        WebElement ratingInput = driver.findElement(By.id("rating"));
        ratingInput.clear();
        ratingInput.sendKeys("8,0");

        driver.findElement(By.id("submit")).click();

        Assert.assertEquals("Vul hier de gegevens van je favoriete voetballer in.", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        Assert.assertTrue(containsWebElementsWithText(lis, "Voornaam en Naam"));
        Assert.assertEquals("Premier League", driver.findElement(By.id("competitie")).getAttribute("value"));
        Assert.assertEquals("Manchester City", driver.findElement(By.id("club")).getAttribute("value"));
        Assert.assertEquals("17", driver.findElement(By.id("nummer")).getAttribute("value"));
        Assert.assertEquals("8,0", driver.findElement(By.id("rating")).getAttribute("value"));
    }

    @Test
    public void test_Overview_is_shown_If_all_fields_are_filled_out_correctly() {
        WebElement voornaam_naamInput = driver.findElement(By.id("voornaam_naam"));
        voornaam_naamInput.clear();
        voornaam_naamInput.sendKeys("Stef Sempels");

        WebElement competitieInput = driver.findElement(By.id("competitie"));
        competitieInput.clear();
        competitieInput.sendKeys("Premier League");

        WebElement clubInput = driver.findElement(By.id("club"));
        clubInput.clear();
        clubInput.sendKeys("Manchester City");

        WebElement nummerInput = driver.findElement(By.id("nummer"));
        nummerInput.clear();
        nummerInput.sendKeys("17");

        WebElement ratingInput = driver.findElement(By.id("rating"));
        ratingInput.clear();
        ratingInput.sendKeys("8,0");

        driver.findElement(By.id("submit")).click();

        Assert.assertEquals("Bekijk alle voetballers", driver.getTitle());

        ArrayList<WebElement> tds = (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        Assert.assertTrue(containsWebElementsWithText(tds, "Stef Sempels"));
    }
}
