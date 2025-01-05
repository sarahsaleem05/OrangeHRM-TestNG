import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrangeHRM {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void LaunchApp() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test(priority = 1)
    public void EnterLoginDetails() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']"))).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-layout-container']")));
        System.out.println("Login successful: " + dashboard.isDisplayed());
    }

    @Test(priority = 2)
    public void NavigateToMyInfo() {
        WebElement myInfoTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/web/index.php/pim/viewMyDetails']")));
        myInfoTab.click();
        System.out.println("Navigated to 'My Info' tab.");
    }

    @Test(priority = 3)
    public void VerifyMyInfo() {
        WebElement myInfoPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='orangehrm-edit-employee']")));
        System.out.println("My Info page is displayed: " + myInfoPage.isDisplayed());
    }

    @Test(priority = 4)
    public void VerifyLogin() {
        WebElement dashboard = driver.findElement(By.xpath("//div[@class='oxd-layout-container']"));
        System.out.println("Dashboard is displayed: " + dashboard.isDisplayed());
    }
   
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
