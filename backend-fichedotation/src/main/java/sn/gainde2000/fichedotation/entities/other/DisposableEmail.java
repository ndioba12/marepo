/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/02/2022
 */

package sn.gainde2000.fichedotation.entities.other;

import lombok.*;

import javax.persistence.*;

@Table(name = "TD_DisposableEmail")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DisposableEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Dis_id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "Dis_domain")
    private String domain;
}
