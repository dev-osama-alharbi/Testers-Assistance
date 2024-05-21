package sa.osama_alharbi.prj.testers.assistance.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManagerSlmService {

    /*
        start
        stop
        generate browser [firefox,chrome,......]
        setMain cookies
     */

    private final ExtensionSlmService extensionSlmService;
    public WebDriver driver;
    public void startStop(){
        if(driver != null){
            driver.quit();
            driver = null;
        }else {
            driver = generateChromeDriver();
        }
    }
    public void startStop(String url){
        if(driver != null){
            driver.quit();
            driver = null;
        }else {
            driver = generateChromeDriver();
            goTo(url);
        }
    }
//    public void startStop(){
//        if (isOpened()){
//            driver.close();
//        }else{
////            driver.switchTo().newWindow(WindowType.WINDOW);
//            System.out.println(driver.getWindowHandle());
////            driver.switchTo().newWindow(WindowType.WINDOW);
//            goTo("https://google.com/");
//        }
//    }
    public void goTo(String url){
        if(isOpened()){
            driver.navigate().to(url);
        }else{
            driver.navigate().to(url);
        }
    }
    public String getUrl(){
        if(isOpened()){
            return driver.getCurrentUrl();
        }else{
            return driver.getCurrentUrl();
        }
    }
    public boolean isOpened(){
//        if(driver.getWindowHandle() == null || driver.getWindowHandle().isEmpty() || driver.getWindowHandle().isBlank()){
//            System.out.println("++++++ "+driver.getWindowHandle());
//            return false;
//        }
        try {
            return !driver.getWindowHandles().isEmpty();
        } catch (NoSuchWindowException e){
            return false;
        } catch (WebDriverException e){
            return false;
        }
    }

    private WebDriver generateChromeDriver(){
        log.info("Opening Chrome Browser");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addExtensions(extensionSlmService.getExtensionZip());
        driver = new ChromeDriver(chromeOptions);
        return driver;
    }
}
