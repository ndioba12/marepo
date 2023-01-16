import {UtilisateursService} from './services/utilisateurs.service';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UtilisateursRoutingModule} from './utilisateurs-routing.module';
import {ListUtilisateurComponent} from './components/list-utilisateur/list-utilisateur.component';
import {AjoutUtilisateurComponent} from './components/ajout-utilisateur/ajout-utilisateur.component';
import {EditUtilisateurComponent} from './components/edit-utilisateur/edit-utilisateur.component';
import {DetailUtilisateurComponent} from './components/detail-utilisateur/detail-utilisateur.component';
import {MaterialUiModule} from '../../@shared/material-ui/material-ui.module';
import {NgxMaskModule} from 'ngx-mask';
import {SharedModule} from '../../@shared/shared.module';


@NgModule({
    declarations: [
        ListUtilisateurComponent,
        AjoutUtilisateurComponent,
        EditUtilisateurComponent,
        DetailUtilisateurComponent
    ],
    imports: [
        CommonModule,
        UtilisateursRoutingModule,
        NgxMaskModule,
        MaterialUiModule,
        SharedModule,
    ],
    providers: [UtilisateursService]
})
export class UtilisateursModule {
}
