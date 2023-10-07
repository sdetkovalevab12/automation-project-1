
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.security.Key;


public class TestProject {
    public static void main(String[] args) throws InterruptedException {

        Faker faker = new Faker();

        WebDriver driver = new ChromeDriver();

        driver.get("http://duotify.us-east-2.elasticbeanstalk.com/register.php");
        Assert.assertEquals(driver.getCurrentUrl(), "http://duotify.us-east-2.elasticbeanstalk.com/register.php");
        Assert.assertEquals(driver.getTitle(), "Welcome to Duotify!");

        driver.findElement(By.id("hideLogin")).click();
        String userName= faker.name().username();
        driver.findElement(By.id("username")).sendKeys(userName, Keys.ENTER);

        String firstName= faker.name().firstName();
        driver.findElement(By.id("firstName")).sendKeys(firstName, Keys.ENTER);

        String lastName=faker.name().lastName();
        driver.findElement(By.id("lastName")).sendKeys(lastName, Keys.ENTER);

        String email = faker.internet().emailAddress();
        driver.findElement(By.id("email")).sendKeys(email, Keys.ENTER);
        driver.findElement(By.id("email2")).sendKeys(email, Keys.ENTER);
        String password = faker.internet().password();
        driver.findElement(By.id("password")).sendKeys(password, Keys.ENTER);
        driver.findElement(By.id("password2")).sendKeys(password);
        driver.findElement(By.name("registerButton")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "http://duotify.us-east-2.elasticbeanstalk.com/browse.php?");
        Assert.assertEquals(firstName+" "+lastName, (driver.findElement(By.id("nameFirstAndLast"))).getText());

        driver.findElement(By.id("nameFirstAndLast")).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.className("userInfo")).getText(), (firstName+" "+lastName));


        driver.findElement(By.id("rafael")).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), "http://duotify.us-east-2.elasticbeanstalk.com/register.php");

        driver.findElement(By.id("loginUsername")).sendKeys(userName, Keys.ENTER);
        driver.findElement(By.id("loginPassword")).sendKeys(password, Keys.ENTER);

        Thread.sleep(1000);
        Assert.assertTrue((driver.getPageSource()).contains("You Might Also Like"));
        Thread.sleep(1000);
        driver.findElement(By.id("nameFirstAndLast")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("rafael")).click();

        Thread.sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), "http://duotify.us-east-2.elasticbeanstalk.com/register.php");
        driver.quit();


    }
}
