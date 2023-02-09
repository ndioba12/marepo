import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UtilisateursRoutingModule } from './utilisateurs-routing.module';
import { AddUtilisateurComponent } from './components/add-utilisateur/add-utilisateur.component';
import { EditUtilisateurComponent } from './components/edit-utilisateur/edit-utilisateur.component';
import { ListUtilisateurComponent } from './components/list-utilisateur/list-utilisateur.component';
import { MonCompteComponent } from './components/mon-compte/mon-compte.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    ListUtilisateurComponent,
    AddUtilisateurComponent,
    EditUtilisateurComponent,
    MonCompteComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    UtilisateursRoutingModule,
    ReactiveFormsModule,

  ]
})
export class UtilisateursModule { }
