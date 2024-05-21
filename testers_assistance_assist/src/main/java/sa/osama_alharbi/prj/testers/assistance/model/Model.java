package sa.osama_alharbi.prj.testers.assistance.model;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.model.models.*;

@Service
@Slf4j
public class Model {
    public ProjectMModel project;
    public PageMModel page;
    public ElementMModel element;
    public PathMModel path;
    public CookiesMModel cookies;
    public GlobalCookiesMModel globalCookies;
    public PathTypeMModel pathType;
    public TestTypeMModel testType;

    @PostConstruct
    public void init(){
        project = new ProjectMModel(this);
        page = new PageMModel(this);
        element = new ElementMModel(this);
        path = new PathMModel(this);
        cookies = new CookiesMModel(this);
        globalCookies = new GlobalCookiesMModel(this);
        pathType = new PathTypeMModel(this);
        testType = new TestTypeMModel(this);
    }
}
