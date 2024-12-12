import React, { useState } from 'react';
import { Container, TextField, Button, Typography, Box } from '@mui/material';
import { initializeTicketPool } from '../../api/TicketPoolService';
import '../../styles/main.scss';

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
    <Container maxWidth="sm" className='container'>
      <Typography variant="h4" component="h1" className='header'>
        Initialize Ticket Pool
      </Typography>
      <Box component="form" onSubmit={handleSubmit} noValidate className='form'>
        <TextField
          label="Total Tickets"
          type="number"
          required
          fullWidth
          margin="normal"
          value={totalTickets}
          onChange={(e) => setTotalTickets(e.target.value)}
          className='input-field'
        />
        <TextField
          label="Tickets Release Rate"
          type="number"
          required
          fullWidth
          margin="normal"
          value={ticketsReleaseRate}
          onChange={(e) => setTicketsReleaseRate(e.target.value)}
          className='input-field'
        />
        <TextField
          label="Max Tickets Capacity"
          type="number"
          required
          fullWidth
          margin="normal"
          value={maxTicketsCapacity}
          onChange={(e) => setMaxTicketsCapacity(e.target.value)}
          className='input-field'
        />
        {message && (
          <Typography className={`${'message'} ${'success'}`}>
            {message}
          </Typography>
        )}
        {error && (
          <Typography className={`${'message'} ${'error'}`}>
            {error}
          </Typography>
        )}
        <Button type="submit" variant="contained" fullWidth className='submit-button'>
          Initialize
        </Button>
      </Box>
    </Container>
  );
};

export default TicketPoolInitialization;
