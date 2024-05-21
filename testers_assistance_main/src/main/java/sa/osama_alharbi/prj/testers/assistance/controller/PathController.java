package sa.osama_alharbi.prj.testers.assistance.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sa.osama_alharbi.prj.testers.assistance.dto.NewPathDto;
import sa.osama_alharbi.prj.testers.assistance.entity.Element;
import sa.osama_alharbi.prj.testers.assistance.entity.Path;
import sa.osama_alharbi.prj.testers.assistance.enums.PathSlmType;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.service.*;

@RestController
@RequestMapping("/api/v1/path")
@Slf4j
@RequiredArgsConstructor
public class PathController {

    private final PageFxController page;
    private final PathService pathService;
    private final ElementService elementService;
    private final CaptureSlmService captureSlmService;
    private final FindPathSlmService findPathSlmService;
    private final CookiesService cookiesService;
    private final Model model;

    // http://localhost:5551/swagger-ui/index.html     == PostMan
    // http://localhost:5551/api/v1/path

    @PostMapping
    @CrossOrigin("*")
    public ResponseEntity<String> addNewElement(@RequestBody NewPathDto newPathDto) {
        Element newElement = elementService.add(Element
                .builder()
                .name("path")
                .fullXpath(newPathDto.getFullXPath())
                .type(PathSlmType.FULL.type)
                .photo("data:image/png;base64," + captureSlmService.capturingScreenshotEvidence(newPathDto.getFullXPath(),PathSlmType.FULL))
                .tag(findPathSlmService.getTagNameStr(newPathDto.getFullXPath()))
                .value("")
                .pathId(0L)
                .pageId(model.page.get.getSelectedPage())
                .build());

        Path newPath = pathService.add(Path
                .builder()
                .path(newElement.getFullXpath())
                .isSelected(true)
                .counter(1)
                .elementId(newElement.getId())
                .pathTypeId(PathSlmType.FULL.id)
                .build());

        elementService.updatePathId(newElement,newPath.getId());

        cookiesService.updateCookiesForPageId(newElement.getPageId());

        return ResponseEntity.ok("{}");
    }
}
