export interface IComment {
    id: number;
    article: {
        id: number;
        title: string;
    }
    user: {
        id: number;
        name?: string;
    };
    content: string;
    created_at: string;
    updated_at: string;
}
