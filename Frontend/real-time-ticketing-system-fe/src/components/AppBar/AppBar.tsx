import React from 'react';
import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import RemoveTicketsModal from '../../pages/TicketPool/RemoveTickets'; // Adjust the import path as needed
import TestModal from '../../pages/TicketPool/Test';

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

     
        {role === 'VENDOR' && (
          <>
            <Button color="inherit" onClick={() => navigate('/ticket-pool/initialize')}>
              Ticket Pool
            </Button>
            <Button color="inherit" onClick={() => navigate('/ticket-pool/add')}>
              Add Tickets
            </Button>
            {/* Add Remove Tickets Modal Button */}
            <RemoveTicketsModal />
          </>
        )}

        {/* User-only Button */}
        {role === 'USER' && (
          <>
            {/* Purchase Tickets Modal */}
            <TestModal />
          </>
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
