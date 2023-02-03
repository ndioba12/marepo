/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :06/12/2021
 */

package sn.gainde2000.fichedotation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.gainde2000.fichedotation.entities.Entite;

import java.util.Optional;

@Repository
public interface EntiteRepository extends JpaRepository<Entite, Integer> {
    Optional<Entite> findByCode(String code);
}
