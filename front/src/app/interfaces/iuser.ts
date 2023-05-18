import { ITheme } from "./itheme";

export interface IUser {
    id: number;
    themes: ITheme[];
    name: string;
    email: string;
    password?: string;
    created_at: string;
    updated_at: string;
}
