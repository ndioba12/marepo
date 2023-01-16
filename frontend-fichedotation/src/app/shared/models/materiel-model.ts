export class Materiel{
    id?:number;
    designationMateriel!: string;
    typeMateriel!: string;
    appellationCommerciale!: string;
    marque!: string;
    modele!: string;
    accessoiresAssocies!: string;
    fournisseur!: string;
    prixAcquisition!: string;
    dateAcquisition!: string;
    localisationGeo!: string;

    numeroSerie!: string;
    adresseMacMateriel!: string;
    typeMaintenance!: string;
    refInterneMateriel!: string;
    etat!:"Reformé"|"Alerte"|"Actif";
    statutMateriel!:"non reférencié"|"affecté"|"reférencié";
}

export class Affectation{
    id?:number;
    dateFiche!: string;
    identFiche!: string;
    refFiche!: string;
    identMateriel!: string;
    adresseMacMateriel!: string;
    refInterneMateriel!: string;
    destinataire!: string;
    dateDotation!: string;
    objetDotation!: string;
    datePrevueRetour!: string;
    localisationGeo!: string;

    avisDestinataire!: string;
    designationMateriel!: string;
    refComMateriel!: string;

    dateRetour!: string;
    avisServiceSupportAssistance!: string;

    typeAvis:"default"|"accepter"|"refuser" = "default";
    newAffectation:boolean = false;
    etat!:"Reformé"|"Alerte"|"Actif";
}
