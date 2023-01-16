import { Injectable } from "@angular/core";
import { InventaireModel } from "../models/inventaire.model";


@Injectable({
  providedIn: 'root'
})
export class InventaireServices {
  elements: InventaireModel[] = [
    {
      id: 1 ,
      designationDuMateriel : 'designation',
      type : 'typemate',
      appellationCommerciale : 'appellation',
      fournisseur : 'fournisseur',
      prixAchat : '15000',
      dateAchat : '03/06/2022',
      Statut : 'Actif' ,
      Detenteur : 'lamine dieme'
  },
    {
      id: 2 ,
      designationDuMateriel : 'designation',
      type : 'typemate',
      appellationCommerciale : 'appellation',
      fournisseur : 'fournisseur',
      prixAchat : '15000',
      dateAchat : '03/06/2022',
      Statut : 'Reformé' ,
      Detenteur : 'lamine dieme'
  },
    {
      id: 3 ,
      designationDuMateriel : 'designation',
      type : 'typemate',
      appellationCommerciale : 'appellation',
      fournisseur : 'fournisseur',
      prixAchat : '15000',
      dateAchat : '03/06/2022',
      Statut : 'Alerte' ,
      Detenteur : 'lamine dieme'
  },
    {
      id: 4 ,
      designationDuMateriel : 'designation',
      type : 'typemate',
      appellationCommerciale : 'appellation',
      fournisseur : 'fournisseur',
      prixAchat : '15000',
      dateAchat : '03/06/2022',
      Statut : 'Alerte' ,
      Detenteur : 'lamine dieme'
  },
    {
      id: 5 ,
      designationDuMateriel : 'designation',
      type : 'typemate',
      appellationCommerciale : 'appellation',
      fournisseur : 'fournisseur',
      prixAchat : '15000',
      dateAchat : '03/06/2022',
      Statut : 'Actif' ,
      Detenteur : 'lamine dieme'
  },
    {
      id: 6,
      designationDuMateriel : 'designation',
      type : 'typemate',
      appellationCommerciale : 'appellation',
      fournisseur : 'fournisseur',
      prixAchat : '15000',
      dateAchat : '03/06/2022',
      Statut : 'Reformé' ,
      Detenteur : 'lamine dieme'
  },
    {
      id: 7 ,
      designationDuMateriel : 'designation',
      type : 'typemate',
      appellationCommerciale : 'appellation',
      fournisseur : 'fournisseur',
      prixAchat : '15000',
      dateAchat : '03/06/2022',
      Statut : 'Actif' ,
      Detenteur : 'lamine dieme'
  },


  ]

  getAllMateriel() : InventaireModel[] {
    return this.elements
  }
  getMaterielById(productId:number) {
     return this.elements.find(element =>element.id === productId)
  }
}
