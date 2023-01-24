/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/08/2022
 */

package sn.gainde2000.fichedotation.services.interfaces.shared;
import sn.gainde2000.fichedotation.entities.TypeCession;
import sn.gainde2000.fichedotation.web.dtos.messages.requests.authentification.EditMonCompteDTO;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.others.TypeCessionDTO;
import sn.gainde2000.fichedotation.web.dtos.others.TypeImmobilisationDTO;

public interface IDonneeReferences {
    Response<Object> listProfil();
    public Response<Object> listStatut();
    Response<Object> listMarque();
    public Response<Object> listypeCession();
    public Response<Object> addTypeCession(TypeCessionDTO model);
   // Response<Object> updateUserTypeCession(EditMonCompteDTO dto);
   // public Response<Object> listTypeImmobilisation();
  //  public Response<Object> saveTypeImmobilisation(TypeImmobilisationDTO model);
}
