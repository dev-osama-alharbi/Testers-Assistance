package sa.osama_alharbi.prj.testers.assistance.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.entity.PathType;
import sa.osama_alharbi.prj.testers.assistance.enums.PathSlmType;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Service
@Slf4j
@RequiredArgsConstructor
public class CaptureSlmService {
    private final ManagerSlmService managerSlmService;

    public byte[] capturingScreenshot(By capturBy) {
        WebElement captureElement = managerSlmService.driver.findElement(capturBy);
        try (InputStream is = Files.newInputStream(captureElement.getScreenshotAs(OutputType.FILE).toPath())) {
            return is.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String capturingScreenshotEvidence(By parentBy,By evidenceBy) {
        WebElement parentElement = managerSlmService.driver.findElement(parentBy);
        WebElement evidenceElement = managerSlmService.driver.findElement(evidenceBy);
        // To highlight the element
        ((JavascriptExecutor) managerSlmService.driver).executeScript("arguments[0].style.border='2px solid red'", evidenceElement);
        String img = parentElement.getScreenshotAs(OutputType.BASE64);
        // To remove border from highlighted element(Optional)
        ((JavascriptExecutor) managerSlmService.driver).executeScript("arguments[0].style.border = \"none\";", evidenceElement);

        return img;
    }
    public String capturingScreenshotEvidence(String evidence, PathSlmType pathSlmType) {
        By by = null;
        switch (pathSlmType){
            case FULL,XPATH -> by = By.xpath(evidence);
            case ID -> by = By.id(evidence);
            case CLASS_NAME -> by = By.className(evidence);
            case NAME -> by = By.name(evidence);
            case CSS_SELECTOR -> by = By.cssSelector(evidence);
            case TAG_NAME -> by = By.tagName(evidence);
            case LINK_TEXT -> by = By.linkText(evidence);
            case PARTIAL_LINK_TEXT -> by = By.partialLinkText(evidence);
        }
        return capturingScreenshotEvidence(by);
    }
    private String capturingScreenshotEvidence(By evidenceBy) {
        WebElement evidenceElement = managerSlmService.driver.findElement(evidenceBy);
        // To highlight the element
        ((JavascriptExecutor) managerSlmService.driver).executeScript("arguments[0].style.border='2px solid red'", evidenceElement);
        String img = ((TakesScreenshot)managerSlmService.driver).getScreenshotAs(OutputType.BASE64);
        // To remove border from highlighted element(Optional)
        ((JavascriptExecutor) managerSlmService.driver).executeScript("arguments[0].style.border = \"none\";", evidenceElement);

        return img;
    }
}
