package sa.osama_alharbi.prj.testers.assistance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PathDTO {
    private Long id;
    private String fullXPath;
    private String path;
    private Boolean isSelected;
    private PathTypeDTO pathType;
}
