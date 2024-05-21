package sa.osama_alharbi.prj.testers.assistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "page")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "is_completed", nullable = false, columnDefinition = "bit")
    private boolean isCompleted;

    @Column(name = "project_id", columnDefinition = "bigint", nullable = false)
    private Long projectId;

    @Column(name = "test_type_id", columnDefinition = "int", nullable = false)
    private Integer testTypeId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(
            foreignKey = @ForeignKey(name="project_page_fk"),
            name = "project_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private Project project;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(
            foreignKey = @ForeignKey(name="test_type_page_fk"),
            name = "test_type_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private TestType testType;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "page_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private List<Cookies> cookies;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "page_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private List<Element> elements;

}
