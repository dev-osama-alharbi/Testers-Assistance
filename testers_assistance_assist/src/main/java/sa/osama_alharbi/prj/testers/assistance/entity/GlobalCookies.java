package sa.osama_alharbi.prj.testers.assistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "global_cookies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalCookies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    private Long id;

    @Column(name = "key_c", nullable = false, columnDefinition = "varchar(50)")
    private String key;

    @Column(name = "value_c", columnDefinition = "Text")
    private String value;

    @Column(name = "project_id", columnDefinition = "bigint", nullable = false)
    private Long projectId;

    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(
            foreignKey = @ForeignKey(name="project_global_cookies_fk"),
            name = "project_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private Project project;

}
