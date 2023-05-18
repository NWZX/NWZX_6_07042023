import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { ITheme } from 'src/app/interfaces/itheme';
import { ThemesService } from 'src/app/services/themes.service';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent {
  public themes$: Observable<ITheme[]>;
  constructor(private themesService: ThemesService) {
    this.themes$ = this.themesService.getThemes();
  }
}
