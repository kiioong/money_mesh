export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginResponse {
  email: string;
  message: string;
}

export interface AuthContextType {
  user: string | null;
  loading: boolean;
  login: (email: string, password: string) => Promise<LoginResult>;
  logout: () => Promise<void>;
  checkAuth: () => Promise<void>;
  register: (email: string, password: string) => Promise<RegisterResult>;
}

export interface LoginResult {
  success: boolean;
  message?: string;
}

export interface RegisterResult {
  success: boolean;
  message?: string;
}
