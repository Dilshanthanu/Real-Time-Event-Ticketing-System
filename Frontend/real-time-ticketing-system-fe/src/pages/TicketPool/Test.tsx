import React, { useState } from 'react';
import {
  Box,
  Button,
  TextField,
  Typography,
  Modal,
  Paper,
} from '@mui/material';
import { removeTickets } from '../../api/TicketPoolService';

const RemoveTicketsModal: React.FC = () => {
  const [ticketCount, setTicketCount] = useState<number>(0);
  const [message, setMessage] = useState<string>('');
  const [open, setOpen] = useState<boolean>(false);

  const handleOpen = () => setOpen(true);
  const handleClose = () => {
    setOpen(false);
    setTicketCount(0); // Reset ticket count when modal is closed
    setMessage('');
  };

  const handleRemove = async () => {
    try {
      await removeTickets(ticketCount);
      setMessage(`Successfully removed ${ticketCount} tickets.`);
    } catch (error: any) {
      setMessage(error.message || 'An error occurred while removing tickets.');
    }
  };

  return (
    <Box>
      {/* Button to Open Modal */}
      <Button variant="contained" color="secondary" onClick={handleOpen}>
        Remove Tickets
      </Button>

      {/* Modal */}
      <Modal open={open} onClose={handleClose}>
        <Paper
          elevation={3}
          sx={{
            p: 4,
            maxWidth: 500,
            margin: 'auto',
            mt: '10%',
            borderRadius: 2,
          }}
        >
          <Typography variant="h4" gutterBottom>
            Remove Tickets
          </Typography>
          <TextField
            label="Number of Tickets"
            type="number"
            fullWidth
            margin="normal"
            value={ticketCount}
            onChange={(e) => setTicketCount(parseInt(e.target.value, 10) || 0)}
          />
          <Button
            variant="contained"
            color="secondary"
            fullWidth
            onClick={handleRemove}
            sx={{
              mt: 2,
              py: 1.2,
              fontSize: '1rem',
            }}
          >
            Remove
          </Button>
          {message && (
            <Typography
              color={message.startsWith('Successfully') ? 'success.main' : 'error.main'}
              sx={{ mt: 2, fontWeight: 500 }}
            >
              {message}
            </Typography>
          )}
        </Paper>
      </Modal>
    </Box>
  );
};

export default RemoveTicketsModal;
