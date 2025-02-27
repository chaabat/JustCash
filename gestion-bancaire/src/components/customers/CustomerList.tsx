import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { 
  Table, TableBody, TableCell, TableHead, TableRow, 
  Typography, Paper, TableContainer, IconButton, Button, TablePagination, Box,
  TextField, InputAdornment
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import AccountBalanceIcon from '@mui/icons-material/AccountBalance';
import SearchIcon from '@mui/icons-material/Search';
import { customerApi } from '../../services/api';
import { Customer } from '../../types';
import CreateCustomer from './CreateCustomer';
import EditCustomer from './EditCustomer';
import { tableStyles } from '../../styles/tableStyles';

const CustomerList = () => {
  const [customers, setCustomers] = useState<Customer[]>([]);
  const [openCreateDialog, setOpenCreateDialog] = useState(false);
  const [openEditDialog, setOpenEditDialog] = useState(false);
  const [selectedCustomer, setSelectedCustomer] = useState<Customer | null>(null);
  const [page, setPage] = useState(0);
  const [totalElements, setTotalElements] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const [searchQuery, setSearchQuery] = useState('');
  const [sortOrder, setSortOrder] = useState<'asc' | 'desc'>('asc');
  const navigate = useNavigate();

  useEffect(() => {
    loadCustomers();
  }, [page, rowsPerPage, searchQuery, sortOrder]);

  const loadCustomers = async () => {
    try {
      const sort = `id,${sortOrder}`;
      const response = await customerApi.getAll(page, rowsPerPage, searchQuery, sort);
      setCustomers(response.data.content);
      setTotalElements(response.data.totalElements);
    } catch (error) {
      console.error('Erreur lors du chargement des clients:', error);
    }
  };

  const handleDelete = async (id: number) => {
    try {
      await customerApi.delete(id);
      loadCustomers();
    } catch (error) {
      console.error('Erreur lors de la suppression:', error);
    }
  };

  const handleEdit = (customer: Customer) => {
    setSelectedCustomer(customer);
    setOpenEditDialog(true);
  };

  const handleChangePage = (_: unknown, newPage: number) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchQuery(event.target.value);
    setPage(0);
  };

  const handleSortChange = () => {
    setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
  };

  return (
    <Box>
      <Typography variant="h4" sx={{ textAlign: 'center' }} gutterBottom>
        Liste des Clients
      </Typography>
      <Button
        variant="contained"
        color="primary"
        onClick={() => setOpenCreateDialog(true)}
        sx={{ mb: 3, display: 'block', margin: '0 auto' }}
      >
        Nouveau Client
      </Button>

      <Box sx={{ marginLeft: 'auto', marginRight: 'auto', width: '300px',marginTop: '10px' }}>
        <TextField
          placeholder="Rechercher par nom"
          variant="outlined"
          value={searchQuery}
          onChange={handleSearchChange}
          sx={{ width: '300px' }}
          InputProps={{
            startAdornment: (
              <InputAdornment position="start">
                <SearchIcon />
              </InputAdornment>
            ),
          }}
        />      
      </Box>
      <TableContainer component={Paper} sx={tableStyles.container}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell 
                sx={{...tableStyles.headerCell, cursor: 'pointer'}}
                onClick={handleSortChange}
              >
                ID {sortOrder === 'asc' ? '↑' : '↓'}
              </TableCell>
              <TableCell sx={tableStyles.headerCell}>Nom</TableCell>
              <TableCell sx={tableStyles.headerCell}>Email</TableCell>
              <TableCell sx={tableStyles.headerCell}>Adresse</TableCell>
              <TableCell sx={tableStyles.headerCell}>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {customers.map((customer) => (
              <TableRow key={customer.id} sx={tableStyles.row}>
                <TableCell>{customer.id}</TableCell>
                <TableCell>{customer.name}</TableCell>
                <TableCell>{customer.email}</TableCell>
                <TableCell>{customer.address}</TableCell>
                <TableCell>
                  <IconButton 
                    onClick={() => navigate(`/customers/${customer.id}/accounts`)}
                    color="primary"
                  >
                    <AccountBalanceIcon />
                  </IconButton>
                  <IconButton 
                    onClick={() => handleEdit(customer)}
                    color="primary"
                  >
                    <EditIcon />
                  </IconButton>
                  <IconButton 
                    onClick={() => handleDelete(customer.id)}
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
      <CreateCustomer
        open={openCreateDialog}
        onClose={() => setOpenCreateDialog(false)}
        onSuccess={loadCustomers}
      />
      {selectedCustomer && (
        <EditCustomer
          open={openEditDialog}
          onClose={() => {
            setOpenEditDialog(false);
            setSelectedCustomer(null);
          }}
          onSuccess={loadCustomers}
          customer={selectedCustomer}
        />
      )}
    </Box>
  );
};

export default CustomerList; 