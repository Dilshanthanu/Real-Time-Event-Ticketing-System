import React, { useEffect, useState } from 'react';
import { Box, Typography, Grid, Paper } from '@mui/material';
import { PieChart, Pie, Cell, Legend, ResponsiveContainer } from 'recharts';
import { getTicketPool, getTicketCount } from '../../api/DashboardService';
import '../../styles/main.scss';

const COLORS = ['#0088FE', '#00C49F'];

const Dashboard: React.FC = () => {
  const [ticketPool, setTicketPool] = useState<any>(null);
  const [availableTickets, setAvailableTickets] = useState<number>(0);
  const [purchasedTickets, setPurchasedTickets] = useState<number>(0);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const poolData = await getTicketPool();
        const availableCount = await getTicketCount('AVAILABLE');
        const purchasedCount = await getTicketCount('PURCHASED');

        setTicketPool(poolData[0]);
        setAvailableTickets(availableCount);
        setPurchasedTickets(purchasedCount);
      } catch (err: any) {
        setError(err.message);
      }
    };

    fetchData();
  }, []);

  if (error) {
    return <Typography color="error">Error: {error}</Typography>;
  }

  const data = [
    { name: 'Available', value: availableTickets },
    { name: 'Purchased', value: purchasedTickets },
  ];

  return (
    <Box className='dashboard-container'>
      <Typography className='dashboard-title' gutterBottom>
        Ticket Pool Dashboard
      </Typography>
      <Grid container spacing={3} className='grid-container'>
        <Grid item xs={12} md={6}>
          <Paper className='paper-card'>
            <Typography className='card-title'>Total Tickets</Typography>
            <Typography className='card-value'>
              {ticketPool?.totalTickets || 0}
            </Typography>
          </Paper>
        </Grid>
        <Grid item xs={12} md={6}>
          <Paper className='paper-card chart-card'>
            <ResponsiveContainer width="100%" height={300} className='chart-wrapper'>
              <PieChart>
                <Pie
                  data={data}
                  dataKey="value"
                  nameKey="name"
                  cx="50%"
                  cy="50%"
                  outerRadius={80}
                  fill="#8884d8"
                  label
                >
                  {data.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                  ))}
                </Pie>
                <Legend verticalAlign="bottom" height={36} />
              </PieChart>
            </ResponsiveContainer>
          </Paper>
        </Grid>
      </Grid>
    </Box>
  );
};

export default Dashboard;
