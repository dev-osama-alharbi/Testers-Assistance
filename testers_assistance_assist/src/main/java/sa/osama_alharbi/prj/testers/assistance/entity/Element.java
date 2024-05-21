package sa.osama_alharbi.prj.testers.assistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "element")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint")
    private Long id;

    @Column(name = "full_xpath", nullable = false)
    private String fullXpath;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "tag", nullable = false)
    private String tag;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "type", nullable = false)
    private String type;

    //TODO it should a blob
    @Column(name = "photo", nullable = false, columnDefinition = "LONGTEXT")
    private String photo;

    @Column(name = "path_id", columnDefinition = "bigint", nullable = true)
    private Long pathId = 0L;

    @Column(name = "page_id", columnDefinition = "bigint", nullable = false)
    private Long pageId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(
            foreignKey = @ForeignKey(name="page_element_fk"),
            name = "page_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private Page page;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "element_id",
            referencedColumnName = "id"
    )
    private List<Path> paths;
}
