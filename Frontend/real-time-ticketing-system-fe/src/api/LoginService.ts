import axios from 'axios';

export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginResponse {
  code: string;
  message: string;
  content: {
    access_token: string;
    email: string;
    id: number;
    role: string;
  };
}

export const login = async (credentials: LoginRequest): Promise<LoginResponse> => {
  const response = await axios.post<LoginResponse>(
    'http://localhost:8080/api/v1/user/login',
    credentials
  );
  return response.data;
};
