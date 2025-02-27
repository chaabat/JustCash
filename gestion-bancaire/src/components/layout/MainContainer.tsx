import { Box } from '@mui/material';
import { ReactNode } from 'react';

interface MainContainerProps {
  children: ReactNode;
}

const MainContainer = ({ children }: MainContainerProps) => {
  return (
    <Box sx={{ 
      minHeight: '100vh',
      backgroundColor: 'background.default',
      pt: 3,
      pb: 6,
      width: '90%',
      overflowX: 'hidden'
    }}>
      <Box sx={{ 
            width: '98vw'
      }}>
        {children}
      </Box>
    </Box>
  );
};

export default MainContainer; 