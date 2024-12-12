import axios from 'axios';


const API_URL = 'http://localhost:8080/api/v1/ticketPool/initialize';

const BASE_URL = 'http://localhost:8080/api/v1/ticketPool';



interface InitializeTicketPoolRequest {
  totalTickets: number;
  ticketsReleaseRate: number;
  maxTicketsCapacity: number;
}


export const initializeTicketPool = async (data: InitializeTicketPoolRequest) => {
  try {
    const response = await axios.post(API_URL, data);
    return response.data;
  } catch (error) {
    if (axios.isAxiosError(error)) {
      // Check if the error is an AxiosError
      throw new Error(
        error.response?.data?.message || 'Failed to initialize the ticket pool'
      );
    } else {
      // Handle unexpected errors
      throw new Error('An unexpected error occurred');
    }
  }
};
    

export const addTicket = async (count: number) => {
  try {
    const response = await axios.put(`${BASE_URL}/addTicket`, { count });
    return response.data; // Returns { code, message, content }
  } catch (error: any) {
    throw new Error(error.response?.data?.message || 'Failed to add tickets');
  }
};


export const purchaseTickets = async (count: number) => {
  try {
    const response = await axios.put(`http://localhost:8080/api/v1/ticketPool/removeTickets`, { count });
    return response.data;
  } catch (error) {
    if (axios.isAxiosError(error) && error.response) {
      throw new Error(error.response.data?.message || 'Failed to purchase tickets');
    }
    throw new Error('An unknown error occurred while purchasing tickets');
  }
};

export const removeTickets = async (count: number) => {
  try {
    const response = await axios.put(
      'http://localhost:8080/api/v1/ticketPool/removeTickets',
      { count } // JSON body
    );
    return response.data;
  } catch (error) {
    if (axios.isAxiosError(error) && error.response) {
      throw new Error(error.response.data?.message || 'Failed to remove tickets');
    }
    throw new Error('An unknown error occurred while removing tickets');
  }
};
