package sn.gainde2000.fichedotation.services.interfaces.GestionMareriels;


import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.others.AjoutMaterielDTO;
import sn.gainde2000.fichedotation.web.dtos.others.ModifMaterielDTO;

public interface IGestionMateriels {
    public Response<Object> addImmobilisation(AjoutMaterielDTO model);
    Response<Object> getImmobilisation(Integer id);
    //Response<Object> listImmobilisations();
    Response<Object> listImmobilisations(int page, int size, String filter);
    Response<Object> updateImmobilisation(Integer id, ModifMaterielDTO dto);
    Response<Object> deleteImmobilisation(Integer id);

}
