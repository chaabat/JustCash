import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';

const Navbar = () => {
  return (
    <AppBar 
      position="static" 
      elevation={0} 
      sx={{ 
        backgroundColor: 'white',
        borderBottom: '1px solid rgba(0, 0, 0, 0.12)',
        width: '101%',
      }}
      
    >
      <Toolbar sx={{ 
        justifyContent: 'space-between',
        minHeight: 70
      }}>
        <Typography 
          variant="h6" 
          component={RouterLink} 
          to="/"
          sx={{ 
            color: 'primary.main',
            textDecoration: 'none',
            fontWeight: 700,
            letterSpacing: 1
          }}
        >
          Gestion Bancaire
        </Typography>
        <Box>
          <Button 
            component={RouterLink} 
            to="/"
            sx={{ 
              mx: 1,
              '&:hover': {
                backgroundColor: 'primary.light',
                color: 'white'
              }
            }}
          >
            Accueil
          </Button>
          <Button 
            component={RouterLink} 
            to="/customers"
          //  variant="contained"
            sx={{ 
              mx: 1,
            //  boxShadow: 2,
              '&:hover': {
                backgroundColor: 'primary.light',
                color: 'white'              }
            }}
          >
            Clients
          </Button>
        </Box>
      </Toolbar>
    </AppBar>
  );
};

export default Navbar; 