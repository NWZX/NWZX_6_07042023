export interface IArticle {
    id: number;
    theme: {
        id: number;
        title?: string;
    }
    user: {
        id: number;
        username?: string;
    }
    title: string;
    content: string;
    created_at: string;
    updated_at: string;
}
