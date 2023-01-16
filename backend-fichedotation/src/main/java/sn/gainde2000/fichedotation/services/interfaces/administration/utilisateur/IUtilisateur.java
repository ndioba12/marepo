package sn.gainde2000.fichedotation.services.interfaces.administration.utilisateur;

import sn.gainde2000.fichedotation.web.dtos.messages.requests.UtilisateurDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;


public interface IUtilisateur {

    Response<Object> saveUtilisateur(UtilisateurDTO dto);
    Response<Object> listUtilisateur(int page, int size, String filter);
    Response<Object> editUtilisateur(Integer id, UtilisateurDTO dto);
    Response<Object> deleteUtilisateur(Integer id);
    Response<Object> changeStatus(Integer id);
    Response<Object> getUtilisateur(Integer id);
}
