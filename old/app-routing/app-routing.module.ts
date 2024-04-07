import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { MainPageComponent } from '../main-page/main-page.component';
import { AuthGuard } from '../auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Add this line
  { path: 'login', component: LoginComponent },
  { path: 'main', component: MainPageComponent, canActivate: [AuthGuard] },
  // ...other routes
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
