package sa.osama_alharbi.prj.testers.assistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cookies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cookies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    private Long id;

    @Column(name = "key_c", nullable = false, columnDefinition = "varchar(50)")
    private String key;

    @Column(name = "value_c", columnDefinition = "Text")
    private String value;

    @Column(name = "page_id", columnDefinition = "bigint", nullable = false)
    private Long pageId;

    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(
            foreignKey = @ForeignKey(name="page_cookies_fk"),
            name = "page_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private Page page;

}
