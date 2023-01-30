export interface UtilisateurDTO {
  id?: number;
  matricule?: string;
  nom?: string;
  prenom?: string;
  email?: string;
  status?: boolean;
  linkedProfil?: any;
  linkedEntite?: any;
}


export enum ECodeProfil {
  PERSONNEL = 'PER',
  ACHATS = 'ACH',
  CONTROLEUR_DE_GESTION = 'CTG',
  COMPTABLE = 'CPT',
  SERVICE_LOGISTIQUE = 'SEL',
  SUPPORT_TECHNIQUE = 'SET',
  CSO = 'CSO',
  CTO = 'CTO',
  SUPERVISEUR = 'SUP',
  ADMINISTRATEUR = 'ADM',
  SUPER_ADMINISTRATEUR = 'SAD',
}

export interface IProfil{
  id: number;
  code?: ECodeProfil;
  libelle?: string;
}

export interface IUtilisateur {
  id: number
  nom: string;
  entite?: string;
  linkedProfil?: IProfil;
  status: boolean;
}

export interface IEntite{
  id: number;
  code?: string;
  libelle?: string;
}

