import { SxProps } from '@mui/material';

export const tableStyles = {
  container: {
    mt: 3,
    p: 0,
    margin: '20px auto',
    backgroundColor: 'background.paper',
    overflow: 'hidden',
    width: '80%',
    '& .MuiTableCell-root': {
      borderColor: 'rgba(224, 224, 224, 1)',
      padding: '16px 24px',
      fontSize: '0.95rem',
    },
    '& .MuiTable-root': {
      width: '100%',
      tableLayout: 'auto',
    }
  } as SxProps,
  
  headerCell: {
    fontWeight: 600,
    backgroundColor: '#f8fafc',
    color: '#475569',
    whiteSpace: 'nowrap',
    textTransform: 'uppercase',
    fontSize: '0.75rem',
    letterSpacing: '0.05em',
    '&.MuiTableCell-root': {
      py: 3
    }
  } as SxProps,

  row: {
    '&:hover': {
      backgroundColor: '#f1f5f9'
    }
  } as SxProps,

  actionButton: {
    minWidth: 'auto',
    p: 1.5,
    m: 0.5,
    '&:hover': {
      backgroundColor: 'rgba(0, 0, 0, 0.04)'
    }
  } as SxProps
}; 