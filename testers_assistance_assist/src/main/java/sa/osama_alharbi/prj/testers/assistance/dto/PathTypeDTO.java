package sa.osama_alharbi.prj.testers.assistance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sa.osama_alharbi.prj.testers.assistance.enums.PathSlmType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PathTypeDTO {
    private Integer id;
    private PathSlmType pathType;
}
