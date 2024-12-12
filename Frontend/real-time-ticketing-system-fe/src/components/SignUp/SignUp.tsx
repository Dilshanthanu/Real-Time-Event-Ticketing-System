import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {
  TextField,
  Button,
  Typography,
  Alert,
  MenuItem,
} from '@mui/material';
import { signUpUser } from '../../api/SignUpService';
import '../../styles/main.scss';

const roles = ['USER', 'VENDOR'];

const SignUp: React.FC = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    id: 0,
    name: '',
    email: '',
    password: '',
    role: '',
  });
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState<string | null>(null);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      setError(null);
      setSuccess(null);
      await signUpUser(formData);
      setSuccess('Sign-up successful!');
      navigate('/login');
    } catch (err: any) {
      setError(err.message);
    }
  };

  return (
    <div className='signup-container'>
      <div className='signup-box'>
        <Typography className='signup-title'>Sign Up</Typography>
        {error && <Alert severity="error" className='alert'>{error}</Alert>}
        {success && <Alert severity="success" className='alert'>{success}</Alert>}
        <form onSubmit={handleSubmit} className='signup-form'>
          <TextField
            label="Name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            fullWidth
            margin="normal"
            required
            className='input-field'
          />
          <TextField
            label="Email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            fullWidth
            margin="normal"
            type="email"
            required
            className='input-field'
          />
          <TextField
            label="Password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            fullWidth
            margin="normal"
            type="password"
            required
            className='input-field'
          />
          <TextField
            label="Role"
            name="role"
            value={formData.role}
            onChange={handleChange}
            fullWidth
            margin="normal"
            select
            required
            className='input-field'
          >
            {roles.map((role) => (
              <MenuItem key={role} value={role}>
                {role}
              </MenuItem>
            ))}
          </TextField>
          <Button
            type="submit"
            variant="contained"
            fullWidth
            className='submit-button'
          >
            Sign Up
          </Button>
          <Button
            variant="text"
            fullWidth
            className='back-button'
            onClick={() => navigate('/login')}
          >
            Back to Login
          </Button>
        </form>
      </div>
    </div>
  );
};

export default SignUp;
