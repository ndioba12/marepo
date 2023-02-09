import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { AuthentificationModule } from "../authentification/authentification.module";
import { DashboardComponent } from './components/dashboard/dashboard.component';


@NgModule({
    declarations: [
        DashboardComponent
    ],
    imports: [
        CommonModule,
        DashboardRoutingModule,
        AuthentificationModule
    ]
})
export class DashboardModule { }
