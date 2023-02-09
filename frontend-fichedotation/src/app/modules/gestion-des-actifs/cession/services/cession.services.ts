import { Injectable } from "@angular/core";
import { CessionModel } from "../models/cession.model";


@Injectable({
  providedIn: 'root'
})
export class cessionServices {
  elements: CessionModel[] = [
    {
      id: 1 ,
      designations : 'designation',
      typeMateriel: 'type' ,
      founisseur : 'libasse' ,
      dateAchat : '30/03/2020',
      prixAchat : '12 200' ,
      etat : 'Actif' ,
      valeurNette : '12 00'
  },
  {
      id: 2 ,
      designations : 'designation',
      typeMateriel: 'type' ,
      founisseur : 'libasse',
      dateAchat : '30/03/2020',
      prixAchat : '12 200' ,
      etat : 'Alerte' ,
      valeurNette : '12 00'
  },
  {
      id: 3 ,
      designations : 'designation',
      typeMateriel: 'type' ,
      founisseur : 'libasse',
      dateAchat : '30/03/2020',
      prixAchat : '12 200' ,
      etat : 'Reformé' ,
      valeurNette : '12 00'
  },
  {
      id: 4 ,
      designations : 'designation',
      typeMateriel: 'type' ,
      founisseur : 'libasse' ,
      dateAchat : '30/03/2020',
      prixAchat : '12 200' ,
      etat : 'Reformé' ,
      valeurNette : '12 00'
  },
  {
      id: 5 ,
      designations : 'designation',
      typeMateriel: 'type' ,
      founisseur : 'libasse' ,
      dateAchat : '30/03/2020',
      prixAchat : '12 200' ,
      etat : 'Alerte' ,
      valeurNette : '12 00'
  },
  {
      id: 6,
      designations : 'designation',
      typeMateriel: 'type' ,
      founisseur : 'libasse' ,
      dateAchat : '30/03/2020',
      prixAchat : '12 200' ,
      etat : 'Actif' ,
      valeurNette : '12 00'
  },

  {
      id: 7 ,
      designations : 'designation',
      typeMateriel: 'type' ,
      founisseur : 'libasse' ,
      dateAchat : '30/03/2020',
      prixAchat : '12 200' ,
      etat : 'Reformé' ,
      valeurNette : '12 00'
  },
  {
      id: 8 ,
      designations : 'designation',
      typeMateriel: 'type' ,
      founisseur : 'libasse' ,
      dateAchat : '30/03/2020',
      prixAchat : '12 200' ,
      etat : 'Actif' ,
      valeurNette : '12 00'
  },

  ]

  getAllMateriel() : CessionModel[] {
    return this.elements
  }
  getMaterielById(productId:number) {
     return this.elements.find(element =>element.id === productId)
  }
}
