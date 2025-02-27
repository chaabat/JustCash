import { useState, useEffect } from 'react';
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  TextField,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  Stack,
  Snackbar,
  Alert,
  Typography
} from '@mui/material';
import { accountApi } from '../../services/api';
import { Account } from '../../types';

interface CreateAccountProps {
  open: boolean;
  onClose: () => void;
  onSuccess: () => void;
  customerId: number;
}

const CreateAccount = ({ open, onClose, onSuccess, customerId }: CreateAccountProps) => {
  const [formData, setFormData] = useState<Omit<Account, "id" | "createdAt">>({
    solde: 0,
    type: 'EPARGNE',
    clientId: customerId
  });
  const [error, setError] = useState<string | null>(null);
  const [existingAccounts, setExistingAccounts] = useState<Account[]>([]);

  useEffect(() => {
    if (open) {
      loadExistingAccounts();
    }
  }, [open, customerId]);

  const loadExistingAccounts = async () => {
    try {
      const response = await accountApi.getAllByCustomerId(customerId, 0, 10);
      setExistingAccounts(response.data.content);
    } catch (error) {
      console.error('Erreur lors du chargement des comptes:', error);
    }
  };

  const isCreateDisabled = existingAccounts.length >= 2 || 
    existingAccounts.some(account => account.type === formData.type);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await accountApi.create(formData);
      onSuccess();
      onClose();
      setFormData({ ...formData, solde: 0, type: 'EPARGNE' });
      setError(null);
    } catch (error: any) {
      console.error('Erreur lors de la création du compte:', error);
      setError(error.response?.data?.message || 'Erreur lors de la création du compte');
    }
  };

  const handleClose = () => {
    setError(null);
  };

  return (
    <>
      <Dialog open={open} onClose={onClose}>
        <form onSubmit={handleSubmit}>
          <DialogTitle>Nouveau Compte</DialogTitle>
          <DialogContent>
            <Stack spacing={2} sx={{ mt: 1, minWidth: 300 }}>
              {existingAccounts.length >= 2 && (
                <Typography color="error">
                  Le client a déjà atteint le nombre maximum de comptes (2)
                </Typography>
              )}
              {existingAccounts.some(account => account.type === formData.type) && (
                <Typography color="error">
                  Le client possède déjà un compte {formData.type === 'EPARGNE' ? "d'épargne" : 'courant'}
                </Typography>
              )}
              <TextField
                label="Solde initial"
                type="number"
                value={formData.solde}
                onChange={(e) => setFormData({ ...formData, solde: Number(e.target.value) })}
                required
                fullWidth
              />
              <FormControl fullWidth>
                <InputLabel>Type de compte</InputLabel>
                <Select
                  value={formData.type}
                  label="Type de compte"
                  onChange={(e) => setFormData({ ...formData, type: e.target.value as 'EPARGNE' | 'COURANT' })}
                >
                  <MenuItem value="EPARGNE">Épargne</MenuItem>
                  <MenuItem value="COURANT">Courant</MenuItem>
                </Select>
              </FormControl>
            </Stack>
          </DialogContent>
          <DialogActions>
            <Button onClick={onClose}>Annuler</Button>
            <Button 
              type="submit" 
              variant="contained" 
              disabled={isCreateDisabled}
            >
              Créer
            </Button>
          </DialogActions>
        </form>
      </Dialog>
      <Snackbar open={!!error} autoHideDuration={6000} onClose={handleClose}>
        <Alert onClose={handleClose} severity="error" sx={{ width: '100%' }}>
          {error}
        </Alert>
      </Snackbar>
    </>
  );
};

export default CreateAccount; 