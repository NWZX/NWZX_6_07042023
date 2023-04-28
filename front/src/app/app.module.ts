import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatIconModule } from '@angular/material/icon';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ThemesComponent } from './pages/themes/themes.component';
import { ArticlesComponent } from './pages/articles/articles.component';
import { ArticleComponent } from './pages/article/article.component';
import { ArticleNewComponent } from './pages/article-new/article-new.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { LayoutComponent } from './cmpt/layout/layout.component';
import { TopbarComponent } from './cmpt/topbar/topbar.component';
import { ArticleCardComponent } from './cmpt/article-card/article-card.component';
import { ThemeCardComponent } from './cmpt/theme-card/theme-card.component';
import { CommentCardComponent } from './cmpt/comment-card/comment-card.component';
import { MobileMenuComponent } from './cmpt/mobile-menu/mobile-menu.component';
import { CustomInputComponent } from './cmpt/custom-input/custom-input.component';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

const materialModule = [
  MatButtonModule,
  MatToolbarModule,
  MatInputModule,
  MatCardModule,
  MatSidenavModule,
  MatProgressSpinnerModule,
  MatIconModule
];

const pagesModule = [
  HomeComponent,
  LoginComponent,
  RegisterComponent,
  ThemesComponent,
  ArticlesComponent,
  ArticleComponent,
  ArticleNewComponent,
  ProfileComponent
];

const cmptModule = [
  LayoutComponent,
  TopbarComponent,
  ArticleCardComponent,
  ThemeCardComponent,
  CommentCardComponent,
  MobileMenuComponent,
  CustomInputComponent
];

@NgModule({
  declarations: [
    AppComponent,
    ...pagesModule,
    ...cmptModule
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    ...materialModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
