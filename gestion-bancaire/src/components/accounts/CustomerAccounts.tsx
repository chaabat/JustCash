import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import {
   Typography, Paper, Table, TableBody,
  TableCell, TableHead, TableRow, TableContainer,
  IconButton, Button, TablePagination, Box
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import { accountApi, customerApi } from '../../services/api';
import { Account, Customer } from '../../types';
import CreateAccount from './CreateAccount';
import { tableStyles } from '../../styles/tableStyles';

const CustomerAccounts = () => {
  const { id } = useParams<{ id: string }>();
  const [accounts, setAccounts] = useState<Account[]>([]);
  const [customer, setCustomer] = useState<Customer | null>(null);
  const [openCreateDialog, setOpenCreateDialog] = useState(false);
  const [page, setPage] = useState(0);
  const [totalElements, setTotalElements] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);

  useEffect(() => {
    if (id) {
      loadCustomerAndAccounts();
    }
  }, [id, page, rowsPerPage]);

  const loadCustomerAndAccounts = async () => {
    try {
      const [customerResponse, accountsResponse] = await Promise.all([
        customerApi.getById(Number(id)),
        accountApi.getAllByCustomerId(Number(id), page, rowsPerPage)
      ]);
      setCustomer(customerResponse.data);
      setAccounts(accountsResponse.data.content);
      setTotalElements(accountsResponse.data.totalElements);
    } catch (error) {
      console.error('Erreur lors du chargement:', error);
    }
  };

  const handleDeleteAccount = async (accountId: number) => {
    try {
      await accountApi.delete(accountId);
      loadCustomerAndAccounts();
    } catch (error) {
      console.error('Erreur lors de la suppression:', error);
    }
  };

  const handleChangePage = (_: unknown, newPage: number) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  return (
    <Box>
      <Typography variant="h4" sx={{ textAlign: 'center' }} gutterBottom>
        Comptes de {customer?.name}
      </Typography>
      <Button
        variant="contained"
        color="primary"
        onClick={() => setOpenCreateDialog(true)}
        sx={{ mb: 3, display: 'block', margin: '0 auto' }}
      >
        Nouveau Compte
      </Button>
      <TableContainer component={Paper} sx={tableStyles.container}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Type</TableCell>
              <TableCell>Solde</TableCell>
              <TableCell>Date de création</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {accounts.map((account) => (
              <TableRow key={account.id}>
                <TableCell>{account.id}</TableCell>
                <TableCell>{account.type}</TableCell>
                <TableCell>{account.solde} €</TableCell>
                <TableCell>{new Date(account.createdAt).toLocaleDateString()}</TableCell>
                <TableCell>
                  <IconButton
                    onClick={() => handleDeleteAccount(account.id)}
                    color="error"
                  >
                    <DeleteIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
        <TablePagination
          component="div"
          count={totalElements}
          page={page}
          onPageChange={handleChangePage}
          rowsPerPage={rowsPerPage}
          onRowsPerPageChange={handleChangeRowsPerPage}
          rowsPerPageOptions={[5, 10, 25]}
          labelRowsPerPage="Lignes par page"
          labelDisplayedRows={({ from, to, count }) => 
            `${from}-${to} sur ${count !== -1 ? count : `plus de ${to}`}`}
          sx={{
            '.MuiTablePagination-toolbar': {
              backgroundColor: 'background.default'
            }
          }}
        />
      </TableContainer>
      <CreateAccount
        open={openCreateDialog}
        onClose={() => setOpenCreateDialog(false)}
        onSuccess={loadCustomerAndAccounts}
        customerId={Number(id)}
      />
    </Box>
  );
};

export default CustomerAccounts; 