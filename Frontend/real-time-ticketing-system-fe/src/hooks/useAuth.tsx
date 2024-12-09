import { useContext } from 'react';
import { AuthContext } from '../context/AuthContext'; // Assuming AuthContext is exported from AuthContext.tsx

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) throw new Error('useAuth must be used within an AuthProvider');
  return context;
};

export {}; // Ensures the file is treated as a module
