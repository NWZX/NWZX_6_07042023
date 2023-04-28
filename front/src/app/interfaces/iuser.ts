export interface IUser {
    id: number;
    theme_ids: number[];
    username: string;
    email: string;
    password?: string;
    created_at: string;
    updated_at: string;
}
