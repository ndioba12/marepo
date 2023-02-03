export interface ListMaterielsAchatDTO {
  id?: number;
  designation?: string;
  fournisseur?: string;
  dateAcquisition?: Date;
  prixAcquisition?: any;
  typeImmobilisation?: any;
  etat?: any;
}

export interface AjoutMaterielAchatDTO {
  id?: number;
  designation?: string;
  description?: string;
  marque?: string;
  modele?: string;
  fournisseur?: string;
  accessoires?: string;
  dureeGarantie?: any;
  dateAcquisition?: Date;
  prixAcquisition?: any;
  typeImmobilisation?: any;
  catImmobilisation?: any;
}

