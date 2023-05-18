import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IArticle } from 'src/app/interfaces/iarticle';
import { IComment } from 'src/app/interfaces/icomment';
import { ArticlesService } from 'src/app/services/articles.service';
import { CommentsService } from 'src/app/services/comments.service';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss'],
})
export class ArticleComponent {
  public hide = true;
  public onError = false;

  public articleId?: number;
  article?: IArticle;
  comments?: IComment[];

  //FIXME: Add validation to form
  public form;

  constructor(
    private articleService: ArticlesService,
    private commentService: CommentsService,
    private router: Router,
    private fb: FormBuilder,
    private route: ActivatedRoute
  ) {
    this.form = this.fb.group({
      content: [
        '',
        [Validators.required, Validators.min(3), Validators.max(500)],
      ],
    });
    try {
      this.articleId = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    } catch (error) {
      console.error('Error parsing article ID:', error);
    }
  }

  public ngOnInit(): void {
    if (!this.articleId) {
      this.router.navigate(['/articles']);
      return;
    }
    this.articleService.getArticle(this.articleId).subscribe({
      next: (article: IArticle) => {
        this.article = article;
      },
    });
    this.commentService.getCommentsByArticle(this.articleId).subscribe({
      next: (comments: IComment[]) => {
        this.comments = comments;
      },
    });
  }
  public submit(): void {
    if (this.form.valid && this.articleId) {
      const commentRequest: Partial<IComment> = {
        article: {
          id: this.articleId,
          title: '',
        },
        content: this.form.value.content as string,
      };
      this.commentService.createComment(commentRequest).subscribe({
        next: (response: IComment) => {
          this.commentService.getCommentsByArticle(this.articleId as number).subscribe({
            next: (comments: IComment[]) => {
              this.comments = comments;
            },
          });
          this.form.reset();
        },
        error: (_) => (this.onError = true),
      });
    }
  }
  public back(): void {
    this.router.navigate(['/articles']);
  }
}
