import client from './client';

export interface LoginRequest {
    email: string;
    password: string;
}

export interface UserInfo {
    id: string;
    email: string;
    fullName: string;
    role: 'ADMIN' | 'MANAGER' | 'CLIENT';
    companyId: string | null;
    companyName: string | null;
}

export interface LoginResponse {
    accessToken: string;
    tokenType: string;
    expiresIn: number;
    user: UserInfo;
}

export interface RegisterRequest {
    email: string;
    password: string;
    fullName: string;
    phone?: string;
    companyName: string;
}

export async function register(req: RegisterRequest): Promise<LoginResponse> {
    const { data } = await client.post<LoginResponse>('/auth/register', req);
    return data;
}

export async function login(req: LoginRequest): Promise<LoginResponse> {
    const { data } = await client.post<LoginResponse>('/auth/login', req);
    return data;
}