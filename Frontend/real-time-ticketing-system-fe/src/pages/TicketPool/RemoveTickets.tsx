import React, { useState } from 'react';
import { Box, Button, Dialog, DialogActions, DialogContent, DialogTitle, Typography, TextField } from '@mui/material';
import { removeTickets } from '../../api/TicketPoolService';

const RemoveTicketsModal: React.FC = () => {
  const [open, setOpen] = useState(false);
  const [ticketCount, setTicketCount] = useState<number>(0);
  const [message, setMessage] = useState<string>('');

  const handleOpen = () => setOpen(true);
  const handleClose = () => {
    setOpen(false);
    setTicketCount(0);
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
    <>
      {/* Button to open modal */}
      <Button variant="contained" color="secondary" onClick={handleOpen}>
        Remove Tickets
      </Button>

      {/* Modal for removing tickets */}
      <Dialog open={open} onClose={handleClose} fullWidth maxWidth="sm">
        <DialogTitle>Remove Tickets</DialogTitle>
        <DialogContent>
          <Box>
            <Typography variant="body1" gutterBottom>
              Enter the number of tickets you want to remove:
            </Typography>
            <TextField
              label="Number of Tickets"
              type="number"
              fullWidth
              margin="normal"
              value={ticketCount}
              onChange={(e) => setTicketCount(parseInt(e.target.value, 10) || 0)}
            />
            {message && (
              <Typography
                color={message.startsWith('Successfully') ? 'success.main' : 'error.main'}
                sx={{ mt: 2 }}
              >
                {message}
              </Typography>
            )}
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
            Cancel
          </Button>
          <Button onClick={handleRemove} color="secondary">
            Remove
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
};

export default RemoveTicketsModal;
