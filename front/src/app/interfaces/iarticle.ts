export interface IArticle {
    id: number;
    theme: {
        id: number;
        title?: string;
    }
    user: {
        id: number;
        name?: string;
    }
    title: string;
    content: string;
    createdAt: string;
    updatedAt: string;
}
