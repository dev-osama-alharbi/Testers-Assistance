package sa.osama_alharbi.prj.testers.assistance.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.entity.Cookies;
import sa.osama_alharbi.prj.testers.assistance.service.on.OnSlmCRUD;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeleniumService {
    private final OnFxService on;
    private final ManagerSlmService managerSlmService;
    private final CookiesSlmService cookiesSlmService;

    @PostConstruct
    public void init(){
        on.onSlmCRUD = new OnSlmCRUD() {
            @Override
            public boolean startStop(String url) {
                managerSlmService.startStop(url);
                return false;
            }

            @Override
            public boolean goTo(String url) {
                managerSlmService.goTo(url);
                return false;
            }

            @Override
            public String getUrl() {
                return managerSlmService.getUrl();
            }
        };
    }
    public List<Cookies> getCurrentCookieListAndAssignToPageId(Long pageId) {
        return cookiesSlmService.getCurentCookieList().stream().map(cookie -> {
            return Cookies
                    .builder()
                    .key(cookie.getName())
                    .value(cookie.getValue())
                    .pageId(pageId)
                    .build();
        }).toList();
    }
}
