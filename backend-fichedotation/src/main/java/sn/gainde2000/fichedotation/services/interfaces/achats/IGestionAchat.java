package sn.gainde2000.fichedotation.services.interfaces.achats;

import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.EditMonCompteDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.UpdatePasswordDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.others.TypeCessionDTO;

public interface IGestionAchat {
    public Response<Object> addImmobilisation(TypeCessionDTO model);
    Response<Object> getImmobilisation();
    Response<Object> listImmobilisations();
    Response<Object> updateImmobilisation(EditMonCompteDTO dto);
    Response<Object> updatePassword(UpdatePasswordDTO updateImmobilisationDTO);
}
