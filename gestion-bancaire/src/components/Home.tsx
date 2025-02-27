import { Typography, Box } from '@mui/material';
import AccountBalanceIcon from '@mui/icons-material/AccountBalance';

const Home = () => {
  return (
    <Box 
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        textAlign: 'center',
        py: { xs: 6, sm: 8, md: 10 },
        width: '100%'
      }}
    >
      <AccountBalanceIcon 
        sx={{ 
          fontSize: 60, 
          color: 'primary.main',
          mb: 4
        }} 
      />
      <Typography 
        variant="h3" 
        gutterBottom
        sx={{
          maxWidth: '800px',
          mb: 3
        }}
      >
        Bienvenue sur l'application de Gestion Bancaire
      </Typography>
      <Typography 
        variant="body1"
        sx={{
          fontSize: '1.125rem',
          color: 'text.secondary',
          maxWidth: '600px'
        }}
      >
        Gérez vos clients et leurs comptes bancaires en toute simplicité.
      </Typography>
    </Box>
  );
};

export default Home; 