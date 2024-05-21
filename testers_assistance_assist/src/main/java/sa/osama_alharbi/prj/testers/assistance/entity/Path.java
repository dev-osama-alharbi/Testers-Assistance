package sa.osama_alharbi.prj.testers.assistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "path")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Path {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "is_selected", nullable = false,columnDefinition = "bit")
    private boolean isSelected;

    @Column(name = "counter", nullable = false)
    private Integer counter;

    @Column(name = "element_id", columnDefinition = "bigint", nullable = false)
    private Long elementId;

    @Column(name = "path_type_id", columnDefinition = "int", nullable = false)
    private Integer pathTypeId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(
            foreignKey = @ForeignKey(name="element_path_fk"),
            name = "element_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private Element element;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(
            foreignKey = @ForeignKey(name="path_type_path_fk"),
            name = "path_type_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private PathType pathType;
}
