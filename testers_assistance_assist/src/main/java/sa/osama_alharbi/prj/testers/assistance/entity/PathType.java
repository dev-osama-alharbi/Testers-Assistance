package sa.osama_alharbi.prj.testers.assistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "path_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PathType {
    @Id
    @Column(columnDefinition = "int")
    private Integer id;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "path_type_id",
            referencedColumnName = "id"
    )
    private List<Path> paths;
}
