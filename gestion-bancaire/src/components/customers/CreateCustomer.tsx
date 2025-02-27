import { useState } from 'react';
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

interface CreateCustomerProps {
  open: boolean;
  onClose: () => void;
  onSuccess: () => void;
}

const CreateCustomer = ({ open, onClose, onSuccess }: CreateCustomerProps) => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    address: ''
  });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await customerApi.create(formData);
      onSuccess();
      onClose();
      setFormData({ name: '', email: '', address: '' });
    } catch (error) {
      console.error('Erreur lors de la création du client:', error);
    }
  };

  return (
    <Dialog open={open} onClose={onClose}>
      <form onSubmit={handleSubmit}>
        <DialogTitle>Nouveau Client</DialogTitle>
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
          <Button type="submit" variant="contained">Créer</Button>
        </DialogActions>
      </form>
    </Dialog>
  );
};

export default CreateCustomer; 