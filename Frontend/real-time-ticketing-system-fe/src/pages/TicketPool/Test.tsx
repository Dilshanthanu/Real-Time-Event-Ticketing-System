import React, { useState } from 'react';
import { Box, Button, TextField, Typography } from '@mui/material';
import { purchaseTickets } from '../../api/TicketPoolService';

const Test: React.FC = () => {
  const [ticketCount, setTicketCount] = useState<number>(0);
  const [message, setMessage] = useState<string>('');

  const handlePurchase = async () => {
    try {
      const response = await purchaseTickets(ticketCount);
      setMessage(`Successfully purchased ${ticketCount} tickets.`);
    } catch (error: any) {
      setMessage(error.message);
    }
  };

  return (
    <Box sx={{ p: 4, maxWidth: 400, margin: 'auto' }}>
      <Typography variant="h4" gutterBottom>Purchase Tickets</Typography>
      <TextField
        label="Number of Tickets"
        type="number"
        fullWidth
        margin="normal"
        value={ticketCount}
        onChange={(e) => setTicketCount(parseInt(e.target.value, 10))}
      />
      <Button variant="contained" color="primary" fullWidth onClick={handlePurchase}>
        Purchase
      </Button>
      {message && <Typography color="success.main" sx={{ mt: 2 }}>{message}</Typography>}
    </Box>
  );
};
 export default Test;