package sa.osama_alharbi.prj.testers.assistance.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.entity.Element;
import sa.osama_alharbi.prj.testers.assistance.entity.Page;
import sa.osama_alharbi.prj.testers.assistance.entity.Path;
import sa.osama_alharbi.prj.testers.assistance.enums.PathSlmType;
import sa.osama_alharbi.prj.testers.assistance.model.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CodingFxService {
    private final Model model;
    private final OnFxService on;

    @PostConstruct
    protected void init(){
        
    }

    public String generateUICodeForPageId(Long pageId){
        StringBuilder code = new StringBuilder();
        Page page = on.onPagesCRUD.getPageById(pageId);
        List<Element> elements = on.onElementCRUD.getAllByPageId(pageId);
        List<Long> pathsIds = elements.stream().map(Element::getPathId).toList();
        Map<Long,Path> pathsMap = new HashMap<>();
        for (Path p:on.onPathCRUD.getPathByIds(pathsIds)){
            pathsMap.put(p.getElementId(),p);
        }

        //TODO import
        code.append("import org.openqa.selenium.By;").append(System.getProperty("line.separator"));
        code.append("import org.openqa.selenium.WebDriver;").append(System.getProperty("line.separator"));
        code.append("import org.openqa.selenium.WebElement;").append(System.getProperty("line.separator"));
        code.append("import org.openqa.selenium.chrome.ChromeDriver;").append(System.getProperty("line.separator"));
        code.append(System.getProperty("line.separator"));

        //TODO class name
        String className = toCamelCase(page.getTitle().replaceAll(" ","_"))+"Page";
        code.append("public class "+className+" {").append(System.getProperty("line.separator"));
        code.append(System.getProperty("line.separator"));

        //TODO WebDriver
        code.append("\tprivate WebDriver driver;").append(System.getProperty("line.separator"));
        code.append(System.getProperty("line.separator"));

        //TODO By && WebElement
        for (Element ele:elements){
            code.append("\t"+getCodeBy(PathSlmType.getTypeById(pathsMap.get(ele.getId()).getPathTypeId()),pathsMap.get(ele.getId()).getPath(),ele.getName())).append(System.getProperty("line.separator"));
            code.append("\t"+getWebElement(ele.getName())).append(System.getProperty("line.separator"));
            code.append(System.getProperty("line.separator"));
        }
        code.append(System.getProperty("line.separator"));

        //TODO constructer
        code.append("\tpublic "+className+"() {").append(System.getProperty("line.separator"));

        //TODO WebDriver =
        code.append("\t\tdriver = new ChromeDriver();").append(System.getProperty("line.separator"));

        //TODO usernameElement =
        for (Element ele:elements){
            code.append("\t\t"+getWebElementEqual(ele.getName())).append(System.getProperty("line.separator"));
        }

        //TODO End
        code.append("\t}").append(System.getProperty("line.separator"));
        code.append("}");



        return code.toString();
    }


    private String getCodePath(PathSlmType pathSlmType, String path){
        return switch (pathSlmType) {
            case FULL,XPATH,ID,TAG_NAME,CLASS_NAME -> path;
            case NAME -> "//*[@name='"+path+"']";
            case CSS_SELECTOR, LINK_TEXT, PARTIAL_LINK_TEXT -> "XXX."+path;
        };
    }

    private String getCodeBy(PathSlmType pathSlmType,String path,String elementName){
        return switch (pathSlmType) {
            case FULL,XPATH -> "private By "+elementName+"By = By.xpath(\""+getCodePath(pathSlmType,path)+"\");";
            case ID -> "private By "+elementName+"By = By.id(\""+getCodePath(pathSlmType,path)+"\");";
            case NAME -> "private By "+elementName+"By = By.name(\""+getCodePath(pathSlmType,path)+"\");";
            case TAG_NAME -> "private By "+elementName+"By = By.tagName(\""+getCodePath(pathSlmType,path)+"\");";
            case CLASS_NAME -> "private By "+elementName+"By = By.className(\""+getCodePath(pathSlmType,path)+"\");";
            case CSS_SELECTOR -> "private By "+elementName+"By = By.cssSelector(\""+getCodePath(pathSlmType,path)+"\");";
            case LINK_TEXT -> "private By "+elementName+"By = By.linkText(\""+getCodePath(pathSlmType,path)+"\");";
            case PARTIAL_LINK_TEXT -> "private By "+elementName+"By = By.partialLinkText(\""+getCodePath(pathSlmType,path)+"\");";
        };
    }
    private String getWebElementEqual(String elementName){
        return elementName+"Element = driver.findElement("+elementName+"By);";
    }
    private String getWebElement(String elementName){
        return "private WebElement "+elementName+"Element;";
    }
    private String toCamelCase(String s){
        String[] parts = s.split("_");
        String camelCaseString = "";
        for (String part : parts){
            camelCaseString = camelCaseString + toProperCase(part);
        }
        return camelCaseString;
    }

    private String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }
}

/*
//TODO import
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//TODO class name
public class TestSelenume {
//TODO WebDriver
//TODO By && WebElement

    private WebDriver driver;
    private By usernameBy = By.id("username");
    private WebElement usernameElement;

//TODO constructer

    public TestSelenume(){
//TODO WebDriver =

        driver = new ChromeDriver();
//TODO usernameElement =

        usernameElement = driver.findElement(usernameBy);
//TODO End

    }
}
 */

