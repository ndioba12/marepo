/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/08/2022
 */

package sn.gainde2000.fichedotation.services.interfaces.shared;
import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;
import sn.gainde2000.fichedotation.web.dtos.others.EntiteDTO;
import sn.gainde2000.fichedotation.web.dtos.others.TypeCessionDTO;

public interface IDonneeReferences {
    Response<Object> listProfil();

    public Response<Object> listStatut();

    public Response<Object> listEtat();

    public Response<Object> listEntite();

    public Response<Object> saveEntite(EntiteDTO model);

    public Response<Object> listypeCession();

    public Response<Object> addTypeCession(TypeCessionDTO model);

    public Response<Object> listTypeMateriels();
    public Response<Object> listCatMateriels();


    public Response<Object> listeTypeMaintenances();


}
