package sa.osama_alharbi.prj.testers.assistance.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.entity.Cookies;
import sa.osama_alharbi.prj.testers.assistance.repo.CookiesRepo;
import sa.osama_alharbi.prj.testers.assistance.service.on.OnCookiesSlmCRUD;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CookiesService {
    private final OnFxService on;
    private final CookiesSlmService cookiesSlmService;
    private final CookiesRepo cookiesRepo;

    @PostConstruct
    public void init(){
        on.onCookiesSlmCRUD = new OnCookiesSlmCRUD() {
            @Override
            public List<Cookies> getAllByPageId(Long pageId) {
                return CookiesService.this.getAllByPageId(pageId);
            }
        };
    }

    public Cookies add(Cookies cookies) {
        return cookiesRepo.save(cookies);
    }

    public List<Cookies> add(List<Cookies> cookiesList) {
        return cookiesRepo.saveAll(cookiesList);
    }

    public Cookies edit(Cookies cookies) {
        return cookiesRepo.save(cookies);
    }

    public List<Cookies> edit(List<Cookies> cookiesList) {
        return cookiesRepo.saveAll(cookiesList);
    }

    public List<Cookies> getAllByPageId(Long pageId) {
        return cookiesRepo.findByPageId(pageId);
    }

    public void updateCookiesForPageId(Long pageId) {
        List<Cookies> cookiesFromDbList = cookiesRepo.findByPageId(pageId);
        List<Cookies> currentList = cookiesSlmService.getCurentCookieList().stream().map(cookie -> {
            return Cookies.builder().build();
        }).toList();

        List<Cookies> newCookies = new ArrayList<>();
        List<Cookies> updateCookies = new ArrayList<>();

        for(Cookies cookiesCurrent:currentList){
            boolean flagNew = true;
            boolean flagUpdate = false;
            long updateIdTmp = 0;
            for(Cookies cookiesFromDb:cookiesFromDbList){
                if(cookiesCurrent.getKey().equals(cookiesFromDb.getKey())){
                    if(!cookiesCurrent.getValue().equals(cookiesFromDb.getValue())){
                        flagUpdate = true;
                        updateIdTmp = cookiesFromDb.getId();
                    }
                    flagNew = false;
                }
            }
            if(flagUpdate && updateIdTmp != 0){
                cookiesCurrent.setId(updateIdTmp);
                cookiesCurrent.setPageId(pageId);
                updateCookies.add(cookiesCurrent);
            }
            if(flagNew){
                cookiesCurrent.setPageId(pageId);
                newCookies.add(cookiesCurrent);
            }

            updateIdTmp = 0;
            flagNew = true;
            flagUpdate = false;
        }

        //TODO Add
        add(newCookies);
        //TODO Edit
        edit(updateCookies);
    }
}
