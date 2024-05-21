package sa.osama_alharbi.prj.testers.assistance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSelenume {
    private WebDriver driver;
    private By usernameBy = By.id("username");
    private WebElement usernameElement;

    public TestSelenume(){
        driver = new ChromeDriver();
        usernameElement = driver.findElement(usernameBy);
    }
}
