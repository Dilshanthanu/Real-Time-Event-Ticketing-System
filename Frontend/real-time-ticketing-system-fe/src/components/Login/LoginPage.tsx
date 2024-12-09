import React, { useState } from 'react';
import { TextField, Button, Typography, Box, Container } from '@mui/material';
import { login } from '../../api/LoginService';
import { useAuth } from '../../context/AuthContext';
import { useNavigate } from 'react-router-dom';
import '../../styles/main.scss';

const LoginPage = () => {
  const { login: saveAuthData } = useAuth();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    try {
      const response = await login({ email, password });
      if (response.code === '00') {
        const { id, access_token } = response.content;
        const role = 'user'; // Replace with actual role if available
        saveAuthData(id, access_token, role);
        navigate('/dashboard');
      } else {
        setError('Invalid credentials');
      }
    } catch (err) {
      setError('Failed to login. Please try again.');
    }
  };

  return (
    <Container maxWidth="sm" className="login-container">
    <Box className="form-box">
      <Typography variant="h4" component="h1" className="title">
        Login
      </Typography>
      <Box component="form" onSubmit={handleSubmit} noValidate className="form">
        <TextField
          className="input-field"
          margin="normal"
          required
          fullWidth
          label="Email Address"
          type="email"
          autoComplete="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <TextField
          className="input-field"
          margin="normal"
          required
          fullWidth
          label="Password"
          type="password"
          autoComplete="current-password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        {error && (
          <Typography className="error-message" variant="body2">
            {error}
          </Typography>
        )}
        <Button
          type="submit"
          fullWidth
          variant="contained"
          className="submit-button"
        >
          Login
        </Button>
      </Box>
    </Box>
  </Container>
  
  );
};

export default LoginPage;
