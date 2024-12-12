import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from '../components/Login/LoginPage';
import SignUpPage from '../components/SignUp/SignUp';
import UserDashboard from '../pages/Dashboard/UserDashboard';
import VendorDashboard from '../pages/Dashboard/Dashboard';
import Layout from '../components/Layout/Layout';
import TicketPoolInitialization from '../pages/TicketPool/TicketPoolInitialization';
import AddTicketScreen from '../pages/TicketPool/AddTicketScreen';
import VendorScreen from '../pages/TicketPool/RemoveTickets';
import { useAuth } from '../context/AuthContext';
import RemoveTickets from '../pages/TicketPool/RemoveTickets';
import Test from '../pages/TicketPool/Test';




interface ProtectedRouteProps {
  children: JSX.Element;
  allowedRoles?: string[];
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ children, allowedRoles }) => {
  const { token, role } = useAuth();

  if (!token) {
    return <Navigate to="/login" replace />;
  }

  if (allowedRoles && !allowedRoles.includes(role ?? '')) {
    return <Navigate to="/" replace />;
  }

  return children;
};

const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/sign-up" element={<SignUpPage />} />
      <Route
        path="/user-dashboard"
        element={
          <ProtectedRoute>
            <Layout>
              <UserDashboard />
            </Layout>
          </ProtectedRoute>
        }
      />
      <Route
        path="/vendor-dashboard"
        element={
          <ProtectedRoute>
            <Layout>
              <VendorDashboard />
            </Layout>
          </ProtectedRoute>
        }
      />
      <Route
        path="/ticket-pool/add"
        element={
          <ProtectedRoute allowedRoles={["vendor"]}>
            <AddTicketScreen />
          </ProtectedRoute>
        }
      />
      <Route
        path="/ticket-pool/initialize"
        element={
          <ProtectedRoute allowedRoles={["vendor"]}>
            <TicketPoolInitialization />
          </ProtectedRoute>
        }
      />
     
      <Route
        path="/vendor-remove"
        element={
          <ProtectedRoute allowedRoles={["vendor"]}>
            <VendorScreen />
          </ProtectedRoute>
        }
      />
      <Route
          path="/purchase-ticket"
          element={
            <ProtectedRoute allowedRoles={['USER']}>
              <Test />
            </ProtectedRoute>
          }
        />
        <Route
          path="/Remove-ticket"
          element={
            <ProtectedRoute allowedRoles={['vendor']}>
              <RemoveTickets />
            </ProtectedRoute>
          }
        />
      <Route path="/" element={<Navigate to="/login" replace />} />
      <Route path="*" element={<Navigate to="/login" replace />} />
    </Routes>
  );
};

export default AppRoutes;
