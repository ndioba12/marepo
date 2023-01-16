/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/08/2022
 */

package sn.gainde2000.fichedotation.services.interfaces.shared;

import sn.gainde2000.fichedotation.web.dtos.messages.responses.Response;

public interface IDonneeReferences {
    Response<Object> listProfil();
    Response<Object> listMarque();
}
