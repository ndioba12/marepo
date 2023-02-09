import { Injectable } from "@angular/core";
import { AFFECTATIONS } from "src/app/shared/models/constantes-model";
import { Affectation } from "src/app/shared/models/materiel-model";

@Injectable({
  providedIn: 'root'
})
export class ReceptionService {

  listDeMesDotations = AFFECTATIONS;
  
  getAllAffectation() : Affectation[] {
    return this.listDeMesDotations
  }
  getAffectationById(item:number) {
     return this.listDeMesDotations.find(element =>element.id === item)
  }
}
