package sa.osama_alharbi.prj.testers.assistance.service.on;

public interface OnSlmCRUD {
    boolean startStop(String url);
    boolean goTo(String url);
    String getUrl();
}
