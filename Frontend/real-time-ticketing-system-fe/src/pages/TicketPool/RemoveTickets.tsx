import React, { useState } from 'react';
import { Box, Button, TextField, Typography } from '@mui/material';
import { removeTickets } from '../../api/TicketPoolService';

const RemoveTickets: React.FC = () => {
  const [ticketCount, setTicketCount] = useState<number>(0);
  const [message, setMessage] = useState<string>('');

  const handleRemove = async () => {
    try {
      const response = await removeTickets(ticketCount);
      setMessage(`Successfully removed ${ticketCount} tickets.`);
    } catch (error: any) {
      setMessage(error.message || 'An error occurred while removing tickets.');
    }
  };

  return (
    <Box sx={{ p: 4, maxWidth: 400, margin: 'auto' }}>
      <Typography variant="h4" gutterBottom>Remove Tickets</Typography>
      <TextField
        label="Number of Tickets"
        type="number"
        fullWidth
        margin="normal"
        value={ticketCount}
        onChange={(e) => setTicketCount(parseInt(e.target.value, 10) || 0)}
      />
      <Button variant="contained" color="secondary" fullWidth onClick={handleRemove}>
        Remove
      </Button>
      {message && <Typography color={message.startsWith('Successfully') ? 'success.main' : 'error.main'} sx={{ mt: 2 }}>{message}</Typography>}
    </Box>
  );
};

export default RemoveTickets;
