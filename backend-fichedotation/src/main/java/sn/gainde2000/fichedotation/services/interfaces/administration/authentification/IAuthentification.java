package sn.gainde2000.fichedotation.services.interfaces.administration.authentification;

import sn.gainde2000.fichedotation.web.dtos.messages.requests.UtilisateurDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.EditMonCompteDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.LoginFormDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.ResetOrForgetFormDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

public interface IAuthentification {
    Response<Object> registerUtilisateur(UtilisateurDTO utilisateurDTO);
    Response<Object> authenticateUser(LoginFormDTO loginRequest);
    Response<Object> authenticateUserWithFirstUrlConnexion(ResetOrForgetFormDTO formRequest);
    Response<Object> authenticateUserWithForgetPasswordUrlConnexion(ResetOrForgetFormDTO formRequest);
    Response<Object> updatePassword(ResetOrForgetFormDTO form);
    Response<Object> initPassword(String login);
    Response<Object> editUserInfos(EditMonCompteDTO req);
    Response<Object> updatePasswordFromInterface(ResetOrForgetFormDTO form);
    Response<Object> checkIfEmailIsUsed(String email);
}
