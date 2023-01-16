import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from '../../@shared/layouts/main/main.component';
import {HomeComponent} from './components/home/home.component';
import {AuthGuard} from '../../@shared/helpers/guards/auth.guard';

const routes: Routes = [
  {
    path: '', component: MainComponent, canActivate: [AuthGuard],
    children: [
      {path: 'home', component: HomeComponent},
      {path: '', redirectTo: 'home', pathMatch: 'full'},
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule {
}
