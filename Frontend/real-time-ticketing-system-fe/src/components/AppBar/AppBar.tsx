import React from 'react';
import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';

const CustomAppBar = () => {
  const { logout, role } = useAuth(); // Assuming logout and role are in your context
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <AppBar position="static" color="primary">
      <Toolbar>
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          Real-Time Event Ticketing System
        </Typography>

        {/* Dashboard Button */}
        <Button color="inherit" onClick={() => navigate('/user/user-dashboard')}>
          Dashboard
        </Button>

        {/* Vendor-only Buttons */}
        {role === 'vendor' && (
          <>
            <Button color="inherit" onClick={() => navigate('/ticket-pool/initialize')}>
              Ticket Pool
            </Button>
            <Button color="inherit" onClick={() => navigate('/ticket-pool/add')}>
              Add Tickets
            </Button>
          </>
        )}

        {/* User-only Button */}
        {role === 'USER' && (
          <Button color="inherit" onClick={() => navigate('/purchase-ticket')}>
            Purchase Tickets
          </Button>
        )}

{role === 'vendor' && (
          <Button color="inherit" onClick={() => navigate('/Remove-ticket')}>
            Remove Tickets
          </Button>
        )}

        {/* Logout Button */}
        <Button color="inherit" onClick={handleLogout}>
          Logout
        </Button>
      </Toolbar>
    </AppBar>
  );
};

export default CustomAppBar;
