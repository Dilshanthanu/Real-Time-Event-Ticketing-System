import React, { createContext, useState, useContext, ReactNode } from 'react';

interface AuthContextType {
  userId: number | null;
  token: string | null;
  role: string | null;
  login: (id: number, token: string, role: string) => void;
  logout: () => void;
}

export const AuthContext = createContext<AuthContextType | undefined>(undefined); // Export AuthContext

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [userId, setUserId] = useState<number | null>(null);
  const [token, setToken] = useState<string | null>(null);
  const [role, setRole] = useState<string | null>(null);

  const login = (id: number, token: string, role: string) => {
    setUserId(id);
    setToken(token);
    setRole(role);
    localStorage.setItem('userId', id.toString());
    localStorage.setItem('token', token);
    localStorage.setItem('role', role);
  };

  const logout = () => {
    setUserId(null);
    setToken(null);
    setRole(null);
    localStorage.clear();
  };

  return (
    <AuthContext.Provider value={{ userId, token, role, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) throw new Error('useAuth must be used within an AuthProvider');
  return context;
};


