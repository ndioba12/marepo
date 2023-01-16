/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/02/2022
 */

package sn.gainde2000.fichedotation.entities.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import sn.gainde2000.fichedotation.security.services.UserPrinciple;

import java.util.Optional;

@Component
public class AuditAwareImpl implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
            return Optional.of(userPrincipal.getId());
        } else return Optional.empty();
    }
}
