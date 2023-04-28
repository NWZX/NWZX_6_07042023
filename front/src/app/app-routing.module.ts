import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { ArticlesComponent } from './pages/articles/articles.component';
import { ArticleComponent } from './pages/article/article.component';
import { ArticleNewComponent } from './pages/article-new/article-new.component';
import { ThemesComponent } from './pages/themes/themes.component';
import { AuthGuard } from './guards/auth.guard';


// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'profile',
    canActivate: [AuthGuard.canActivate],
    component: ProfileComponent,
  },
  {
    path: 'articles',
    canActivate: [AuthGuard.canActivate],
    component: ArticlesComponent,
  },
  {
    path: 'articles/new',
    canActivate: [AuthGuard.canActivate],
    component: ArticleNewComponent,
  },
  {
    path: 'articles/:id',
    canActivate: [AuthGuard.canActivate],
    component: ArticleComponent,
  },
  {
    path: 'themes',
    canActivate: [AuthGuard.canActivate],
    component: ThemesComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
