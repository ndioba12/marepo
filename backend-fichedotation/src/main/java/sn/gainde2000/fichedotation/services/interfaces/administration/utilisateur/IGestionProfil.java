package sn.gainde2000.fichedotation.services.interfaces.administration.utilisateur;

import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.EditMonCompteDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.UpdatePasswordDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;

public interface IGestionProfil {
    Response<Object> getConnectedUser();
    Response<Object> updateUserProfil(EditMonCompteDTO dto);
    Response<Object> updatePassword(UpdatePasswordDTO updatePasswordDTO);
}
