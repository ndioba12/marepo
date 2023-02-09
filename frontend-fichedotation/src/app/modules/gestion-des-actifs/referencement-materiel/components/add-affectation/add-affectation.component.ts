import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbTypeahead } from '@ng-bootstrap/ng-bootstrap';
import { Observable, Subject, merge, OperatorFunction } from 'rxjs';
import { debounceTime, distinctUntilChanged, filter, map } from 'rxjs/operators';


const utilisateurs: { matricule: string; prenom: string; nom: string }[] = [
	{ matricule: 'mat001', prenom: 'Adama', nom: 'GUEYE' },
	{ matricule: 'mat002', prenom: 'Alioune Badara', nom: 'NDIAYE' },
	{ matricule: 'mat003', prenom: 'Aysatou', nom: 'NDIAYE' },
	{ matricule: 'mat004', prenom: 'El Hadj Niang', nom: 'DIOP' },
	{ matricule: 'mat005', prenom: 'Fatou Aissatou', nom: 'CISSÉ' },
	{ matricule: 'mat006', prenom: 'Lamine', nom: 'DIEME' },
	{ matricule: 'mat007', prenom: 'Libasse', nom: 'YADE' }
];
@Component({
	selector: 'app-add-affectation',
	templateUrl: './add-affectation.component.html',
	styleUrls: ['./add-affectation.component.css']
})
export class AddAffectationComponent implements OnInit {

	constructor() { }

	ngOnInit(): void {
	}
	
	model: any;

	@ViewChild('destinataire', { static: true }) destinataire!: NgbTypeahead;
	focus$ = new Subject<string>();
	click$ = new Subject<string>();

	search: OperatorFunction<string, readonly { matricule: string; prenom: string;nom: string }[]> = (text$: Observable<string>) => {
		const debouncedText$ = text$.pipe(debounceTime(200), distinctUntilChanged());
		const clicksWithClosedPopup$ = this.click$.pipe(filter(() => !this.destinataire.isPopupOpen()));
		const inputFocus$ = this.focus$;

		return merge(debouncedText$, inputFocus$, clicksWithClosedPopup$).pipe(
			map((term) =>
				(
					term === '' 
					? utilisateurs
					: utilisateurs.filter((v) => v.matricule.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10),
					
			),
		);
	};
	
	formatter = (x: { prenom: string,nom: string }) => x.prenom + ' ' + x.nom;
}
