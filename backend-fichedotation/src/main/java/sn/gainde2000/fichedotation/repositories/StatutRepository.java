/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :06/12/2021
 */

package sn.gainde2000.fichedotation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.gainde2000.fichedotation.entities.Statut;

import java.util.Optional;

@Repository
public interface StatutRepository extends JpaRepository<Statut, Integer> {
    Optional<Statut> findByCode(String code);
    Statut findStatutByCode(String code);
}
