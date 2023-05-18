import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { IArticle } from 'src/app/interfaces/iarticle';
import { ITheme } from 'src/app/interfaces/itheme';
import { ArticlesService } from 'src/app/services/articles.service';
import { ThemesService } from 'src/app/services/themes.service';

@Component({
  selector: 'app-article-new',
  templateUrl: './article-new.component.html',
  styleUrls: ['./article-new.component.scss'],
})
export class ArticleNewComponent {
  public hide = true;
  public onError = false;

  //FIXME: Add validation to form
  public form;
  public themes$;

  constructor(
    private articleService: ArticlesService,
    private themeService: ThemesService,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.form = this.fb.group({
      title: ['', [Validators.required, Validators.min(3), Validators.max(50)]],
      content: [
        '',
        [Validators.required, Validators.min(3), Validators.max(500)],
      ],
      theme: [0, [Validators.required]],
    });
    this.themes$ = this.themeService.getThemes();
  }

  public submit(): void {
    const articleRequest: Partial<IArticle> = {
      title: this.form.value.title as string | undefined,
      content: this.form.value.content as string | undefined,
      theme: {
        id: this.form.value.theme as number,
      },
    };
    this.articleService.createArticle(articleRequest).subscribe({
      next: (response: IArticle) => {
        this.router.navigate(['/articles']);
      },
      error: (_) => (this.onError = true),
    });
  }
  public back(): void {
    this.router.navigate(['/articles']);
  }
}
