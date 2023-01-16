package sn.gainde2000.fichedotation.security.jwt.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import sn.gainde2000.fichedotation.security.jwt.JwtProvider;
import sn.gainde2000.fichedotation.security.services.UserPrinciple;
import sn.gainde2000.fichedotation.security.utils.AuthUtils;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.authentification.JwtResponseDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@WebFilter("/*")
@Component
public class ApplicationFilter implements Filter {
    @Autowired
    private JwtProvider tokenProvider;

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        try {

            String jwt = AuthUtils.getJwt(httpServletRequest);
            if (jwt != null && tokenProvider.validateJwtToken(jwt)) {
                Authentication authentication = SecurityContextHolder.getContext()
                        .getAuthentication();

                UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
                String refreshToken = tokenProvider.generateJwtRefreshToken(jwt);
                JwtResponseDTO response = new JwtResponseDTO(refreshToken, userPrincipal.getUsername(), userPrincipal.getAuthorities());

                ObjectMapper mapper = new ObjectMapper();
                String stringResp = mapper.writeValueAsString(response);
                httpServletResponse.addHeader("refreshToken", stringResp);


            }
        } catch (Exception e) {
            System.out.println("Exception capturée dans ==> ApplicationFilter");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
