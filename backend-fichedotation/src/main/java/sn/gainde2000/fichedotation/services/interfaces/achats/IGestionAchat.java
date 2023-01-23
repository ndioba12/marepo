package sn.gainde2000.fichedotation.services.interfaces.achats;


import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.others.AjoutAchatDTO;

public interface IGestionAchat {
    public Response<Object> addImmobilisation(AjoutAchatDTO model);
    Response<Object> getImmobilisation(Integer id);
    Response<Object> listImmobilisations();
    Response<Object> updateImmobilisation(Integer id,AjoutAchatDTO dto);

}
