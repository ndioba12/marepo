import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {UtilisateursService} from '../../services/utilisateurs.service';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {ApiPaginator} from '../../../../@shared/models/meta-data.model';
import {ActivatedRoute, Router} from '@angular/router';
import {Alert, AlertService} from '../../../../@shared/helpers/others/alert.service';
import {CredentialsService} from '../../../authentification/services/credentials.service';

@Component({
  selector: 'app-list-utilisateur',
  templateUrl: './list-utilisateur.component.html',
  styleUrls: ['./list-utilisateur.component.css'],
})
export class ListUtilisateurComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['prenom', 'nom', 'email', 'telephone', 'profil', 'compte', 'actions'];
  dataSource: MatTableDataSource<any>;
  userProfilCode = '';

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  pageOptions: any = {page: 0, size: 10};
  pageSizeOptions: number[] = [10, 25, 50, 100];
  pageMetaData: ApiPaginator;
  filterValue = '';

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private alertService: AlertService,
    private utilisateurService: UtilisateursService,
    private credentialsService: CredentialsService
  ) {
    this.dataSource = new MatTableDataSource();
    this.userProfilCode = this.credentialsService.userInfos?.linkedProfil?.code;
  }

  ngOnInit(): void {
    this.listUtilisateur(this.pageOptions);
  }

  applyFilter(event: Event): void {
    this.filterValue = (event.target as HTMLInputElement).value;
    event.stopPropagation();
    this.listUtilisateur(this.pageOptions);
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  paginateData(event: PageEvent): void {
    this.pageOptions = {page: event?.pageIndex, size: event.pageSize};
    this.listUtilisateur(this.pageOptions);
  }

  toAddPage(): void {
    this.router.navigate(['add'], {relativeTo: this.route.parent});
  }

  listUtilisateur(pageOptions: any): void {
    this.utilisateurService.listUtilisateur(pageOptions?.page, pageOptions?.size, this.filterValue).subscribe(data => {
      if (data?.status === 'OK') {
        this.dataSource.data = data?.payload;
        setTimeout(() => {
          this.paginator.pageIndex = this.pageOptions?.page;
          this.paginator.length = data?.metadata.totalElements;
        });
        this.pageMetaData = data?.metadata;
        this.dataSource.sort = this.sort;
      } else {
        this.alertService.showAlert({status: data?.status, message: data?.message, titre: 'Utilisateurs'});
      }
    });
  }

  deleteUtilisateur(element): void {
    this.alertService.showSwal('CONFIRM', () => {
      this.utilisateurService.deleteUtilisateur(element?.id).subscribe(rsp => {
        if (rsp?.status === 'OK') {
          const alert: Alert = {status: rsp?.status, message: rsp?.message, titre: 'Utilisateurs'};
          this.alertService.showAlert(alert);
          this.listUtilisateur(this.pageOptions);
        }
      });
    });
  }

  changeStatus(element): void {
    this.alertService.showSwal('CONFIRM', () => {
      this.utilisateurService.changeStatus(element?.id).subscribe(rsp => {
        if (rsp?.status === 'OK') {
          const alert: Alert = {status: rsp?.status, message: rsp?.message, titre: 'Utilisateurs'};
          this.alertService.showAlert(alert);
          this.listUtilisateur(this.pageOptions);
        }
      });
    });
  }

  toDetailsPage(element): void {
    this.router.navigateByUrl(`/users/details/${element?.id}`);
  }

  toEditPage(element): void {
    this.router.navigateByUrl(`/users/edit/${element?.id}`);
  }
}


