import { useState, useEffect } from 'react';
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  TextField,
  Stack
} from '@mui/material';
import { customerApi } from '../../services/api';
import { Customer } from '../../types';

interface EditCustomerProps {
  open: boolean;
  onClose: () => void;
  onSuccess: () => void;
  customer: Customer;
}

const EditCustomer = ({ open, onClose, onSuccess, customer }: EditCustomerProps) => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    address: ''
  });

  useEffect(() => {
    if (customer) {
      setFormData({
        name: customer.name,
        email: customer.email,
        address: customer.address
      });
    }
  }, [customer]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await customerApi.update(customer.id, formData);
      onSuccess();
      onClose();
    } catch (error) {
      console.error('Erreur lors de la modification du client:', error);
    }
  };

  return (
    <Dialog open={open} onClose={onClose}>
      <form onSubmit={handleSubmit}>
        <DialogTitle>Modifier le Client</DialogTitle>
        <DialogContent>
          <Stack spacing={2} sx={{ mt: 1 }}>
            <TextField
              label="Nom"
              value={formData.name}
              onChange={(e) => setFormData({ ...formData, name: e.target.value })}
              required
              fullWidth
            />
            <TextField
              label="Email"
              type="email"
              value={formData.email}
              onChange={(e) => setFormData({ ...formData, email: e.target.value })}
              required
              fullWidth
            />
            <TextField
              label="Adresse"
              value={formData.address}
              onChange={(e) => setFormData({ ...formData, address: e.target.value })}
              required
              fullWidth
            />
          </Stack>
        </DialogContent>
        <DialogActions>
          <Button onClick={onClose}>Annuler</Button>
          <Button type="submit" variant="contained" color="primary">
            Enregistrer
          </Button>
        </DialogActions>
      </form>
    </Dialog>
  );
};

export default EditCustomer; 