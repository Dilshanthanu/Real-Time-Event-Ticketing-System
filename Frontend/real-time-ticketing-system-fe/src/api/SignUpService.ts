import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/v1/user';

export const signUpUser = async (userData: {
  id: number;
  name: string;
  email: string;
  password: string;
  role: string;
}) => {
  try {
    const response = await axios.post(`${BASE_URL}/sign_up`, userData);
    return response.data;
  } catch (error: any) {
    throw new Error(error.response?.data?.message || 'Sign-up failed');
  }
};
