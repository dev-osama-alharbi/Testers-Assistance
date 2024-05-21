package sa.osama_alharbi.prj.testers.assistance.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.dto.PathDTO;
import sa.osama_alharbi.prj.testers.assistance.dto.PathTypeDTO;
import sa.osama_alharbi.prj.testers.assistance.enums.PathSlmType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindPathSlmService {
    private final ManagerSlmService managerSlmService;

    public List<PathDTO> getRecommendedPath(String fullXPath){
        List<PathDTO> result = new ArrayList<>();
        result.add(PathDTO
                .builder()
                .id(0L)
                .fullXPath(fullXPath)
                .path(fullXPath)
                .isSelected(false)
                .pathType(PathTypeDTO.builder().id(0).pathType(PathSlmType.FULL).build())
                .build());
        //TODO Not Null
        PathDTO id = getId(fullXPath);
        if(id != null){
            result.add(id);
        }
        PathDTO name = getName(fullXPath);
        if(name != null){
            result.add(name);
        }
        PathDTO className = getClassName(fullXPath);
        if(className != null){
            result.add(className);
        }
        PathDTO tagName = getTagName(fullXPath);
        if(tagName != null){
            result.add(tagName);
        }
        return result;
    }

    private PathDTO getTagName(String fullXPath){
//        WebElement element = managerSlmService.driver.findElement(By.xpath(fullXPath));
        return PathDTO
                .builder()
                .id(0L)
                .fullXPath(fullXPath)
                .path(getTagNameStr(fullXPath))
                .isSelected(false)
                .pathType(PathTypeDTO.builder().id(0).pathType(PathSlmType.TAG_NAME).build())
                .build();
    }

    public String getTagNameStr(String fullXPath){
        WebElement element = managerSlmService.driver.findElement(By.xpath(fullXPath));
        return element.getTagName();
    }

    private PathDTO getClassName(String fullXPath){
        List<PathDTO> result = getBy(PathSlmType.CLASS_NAME,fullXPath);
        if (!result.isEmpty()) {
            if(result.get(0).getPath().split(" ").length > 0){
                return result.get(0);
            }
        }
        return null;
    }

    private PathDTO getId(String fullXPath){
        List<PathDTO> result = getBy(PathSlmType.ID,fullXPath);
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    private PathDTO getName(String fullXPath){
        List<PathDTO> result = getBy(PathSlmType.NAME,fullXPath);
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
    private List<PathDTO> getBy(PathSlmType pathSlmType,String fullXPath){
        List<PathDTO> result = new ArrayList<>();
        WebElement element = managerSlmService.driver.findElement(By.xpath(fullXPath)); // Your element
        JavascriptExecutor executor = (JavascriptExecutor) managerSlmService.driver;
        Object args=executor.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", element);
        if(args instanceof Map){
            Map<String,String> argsMap = (Map<String,String>)args;
            for (Map.Entry<String,String> ent : argsMap.entrySet())
                if(ent.getKey().equalsIgnoreCase(pathSlmType.type))
                    result.add(PathDTO
                            .builder()
                            .id(0L)
                            .fullXPath(fullXPath)
                            .path(ent.getValue())
                            .isSelected(false)
                            .pathType(PathTypeDTO.builder().id(0).pathType(pathSlmType).build())
                            .build());
        }
        return result;
    }
    public int countBy(PathSlmType pathSlmType,String path){
        return switch (pathSlmType) {
            case FULL,XPATH -> countBy(By.xpath(path));
            case ID -> countBy(By.id(path));
            case NAME -> countBy(By.name(path));
            case TAG_NAME -> countBy(By.tagName(path));
            case CLASS_NAME -> countBy(By.className(path));
            case CSS_SELECTOR -> countBy(By.cssSelector(path));
            case LINK_TEXT -> countBy(By.linkText(path));
            case PARTIAL_LINK_TEXT -> countBy(By.partialLinkText(path));
        };
    }
    private int countBy(By by){
        return managerSlmService.driver.findElements(by).size();
    }
}
