package sa.osama_alharbi.prj.testers.assistance.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.controller.*;

@Service
@Slf4j
public class ControllerFxService {
    public RootFxController root;
    public PageFxController page;
    public Page_InfoFxController pageInfo;
    public Page_PathFxController pagePath;
    public PagesFxController pages;
    public ProjectFxController project;
    public Root_HeaderFxController root_HeaderFxController;
    public Pages_InfoFxController pages_info;
    public Pages_CookiesFxController pages_cookies;
    public Pages_CodeFxController pages_code;
}
