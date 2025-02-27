import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import App from './App';
import Home from './components/Home';
import CustomerList from './components/customers/CustomerList';
import CustomerAccounts from './components/accounts/CustomerAccounts';

// Configuration des drapeaux de fonctionnalités futures
export const router = createBrowserRouter(
  [
    {
      path: '/',
      element: <App />,
      children: [
        {
          index: true,
          element: <Home />,
        },
        {
          path: 'customers',
          element: <CustomerList />,
        },
        {
          path: 'customers/:id/accounts',
          element: <CustomerAccounts />,
        },
      ],
    },
  ],
  {
    future: {
      // Ces drapeaux seront activés par défaut dans React Router v7
      // Nous les activons maintenant pour préparer la migration
      v7_relativeSplatPath: true,
    },
  }
);

export default function Router() {
  return <RouterProvider router={router} />;
} 