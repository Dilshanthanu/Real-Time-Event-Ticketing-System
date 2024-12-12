import React, { useState } from 'react';
import { TextField, Button, Typography, Box, Container } from '@mui/material';
import { addTicket } from '../../api/TicketPoolService';
import '../../styles/main.scss';

const AddTicketScreen: React.FC = () => {
  const [count, setCount] = useState(10); // Default value for tickets to add
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const handleAddTicket = async () => {
    try {
      const response = await addTicket(count);
      if (response.code === '00') {
        setMessage(`Tickets added successfully! Total: ${response.content}`);
        setError('');
      } else {
        setError('Failed to add tickets');
        setMessage('');
      }
    } catch (err: any) {
      setError(err.message);
      setMessage('');
    }
  };

  return (
    <Container maxWidth="sm" className="add-ticket-container">
      <Box className="form-box" sx={{ mt: 4 }}>
        <Typography variant="h4" component="h1" className="title">
          Add Tickets to Pool
        </Typography>
        <Box component="form" noValidate className="form" onSubmit={(e) => e.preventDefault()}>
          <TextField
            className="input-field"
            margin="normal"
            required
            fullWidth
            label="Number of Tickets"
            type="number"
            value={count}
            onChange={(e) => setCount(Number(e.target.value))}
          />
          <Button
            type="button"
            fullWidth
            variant="contained"
            color="primary"
            className="submit-button"
            sx={{ mt: 2 }}
            onClick={handleAddTicket}
          >
            Add Tickets
          </Button>
          {message && (
            <Typography color="success.main" variant="body2" sx={{ mt: 2 }}>
              {message}
            </Typography>
          )}
          {error && (
            <Typography color="error" variant="body2" sx={{ mt: 2 }}>
              {error}
            </Typography>
          )}
        </Box>
      </Box>
    </Container>
  );
};

export default AddTicketScreen;
