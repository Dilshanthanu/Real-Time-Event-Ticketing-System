import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/v1';

export const getTicketPool = async () => {
    try {
      const response = await axios.get(`${BASE_URL}/ticketPool/getTicketPool`);
      return response.data;
    } catch (error) {
      throw new Error('Failed to fetch ticket pool data');
    }
  };
  
  export const getTicketCount = async (status: string) => {
    try {
      const response = await axios.get(`${BASE_URL}/ticket/getTicket`, {
        params: { Status: status },
      });
      return response.data;
    } catch (error) {
      throw new Error(`Failed to fetch ticket count for status: ${status}`);
    }
  };