import React, { useState } from 'react';
import { Container, TextField, Button, Typography, Box } from '@mui/material';
import { initializeTicketPool } from '../../api/TicketPoolService';

const TicketPoolInitialization = () => {
  const [totalTickets, setTotalTickets] = useState<number | string>('');
  const [ticketsReleaseRate, setTicketsReleaseRate] = useState<number | string>('');
  const [maxTicketsCapacity, setMaxTicketsCapacity] = useState<number | string>('');
  const [message, setMessage] = useState<string>('');
  const [error, setError] = useState<string>('');

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    setMessage('');
    setError('');
    try {
      const data = {
        totalTickets: Number(totalTickets),
        ticketsReleaseRate: Number(ticketsReleaseRate),
        maxTicketsCapacity: Number(maxTicketsCapacity),
      };
      const response = await initializeTicketPool(data);
      if (response.code === '00') {
        setMessage(response.message);
      } else {
        setError('Failed to initialize the ticket pool');
      }
    } catch (err) {
        if (err instanceof Error) {
            setError(err.message); // Access the message safely
          } else {
            setError('An unexpected error occurred.');
          }
    }
  };

  return (
    <Container maxWidth="sm" sx={{ mt: 4 }}>
      <Typography variant="h4" component="h1" gutterBottom>
        Initialize Ticket Pool
      </Typography>
      <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 2 }}>
        <TextField
          label="Total Tickets"
          type="number"
          required
          fullWidth
          margin="normal"
          value={totalTickets}
          onChange={(e) => setTotalTickets(e.target.value)}
        />
        <TextField
          label="Tickets Release Rate"
          type="number"
          required
          fullWidth
          margin="normal"
          value={ticketsReleaseRate}
          onChange={(e) => setTicketsReleaseRate(e.target.value)}
        />
        <TextField
          label="Max Tickets Capacity"
          type="number"
          required
          fullWidth
          margin="normal"
          value={maxTicketsCapacity}
          onChange={(e) => setMaxTicketsCapacity(e.target.value)}
        />
        {message && (
          <Typography color="success.main" sx={{ mt: 2 }}>
            {message}
          </Typography>
        )}
        {error && (
          <Typography color="error" sx={{ mt: 2 }}>
            {error}
          </Typography>
        )}
        <Button type="submit" variant="contained" fullWidth sx={{ mt: 2 }}>
          Initialize
        </Button>
      </Box>
    </Container>
  );
};

export default TicketPoolInitialization;
