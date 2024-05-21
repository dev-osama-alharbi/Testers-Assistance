package sa.osama_alharbi.prj.testers.assistance.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StartStopPageFxService {
    private Runnable onStartStop;
    public void startStop() {
        onStartStop.run();
    }
    public void onStartStop(Runnable onStartStop) {
        this.onStartStop = onStartStop;
    }
}
