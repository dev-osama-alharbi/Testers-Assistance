package sa.osama_alharbi.prj.testers.assistance.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
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
public class CookiesSlmService {
    private final ManagerSlmService managerSlmService;

    public List<Cookie> getCurentCookieList(){
        return managerSlmService.driver.manage().getCookies().stream().toList();
    }
}
