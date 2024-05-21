package sa.osama_alharbi.prj.testers.assistance.config;

import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import sa.osama_alharbi.prj.testers.assistance.service.GUIFxService;

@Component
@RequiredArgsConstructor
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    private final GUIFxService gui;
//    private final Action action;
    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        start(stage);
    }

    private void start(Stage stage) {
        initApp();
        gui.start(stage);
//        action.start();
    }

    private void initApp(){

    }
}