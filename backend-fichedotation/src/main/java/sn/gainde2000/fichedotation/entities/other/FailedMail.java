/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/02/2022
 */

package sn.gainde2000.fichedotation.entities.other;

import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Table(name = "TD_FailedMail")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Where(clause = "Fai_is_sent = false")
public class FailedMail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Fai_id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "Fai_email")
    private String email;

    @Column(name = "Fai_subject")
    private String subject;

    @Column(name = "Fai_text", columnDefinition = "TEXT")
    private String text;

    @Column(name = "Fai_is_sent", columnDefinition = "boolean default false")
    private Boolean isSent;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    // @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    protected Date createdDate;
}
