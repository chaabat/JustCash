import { Outlet } from 'react-router-dom';
import Navbar from './components/layout/Navbar';
import MainContainer from './components/layout/MainContainer';

function App() {
  return (
    <>
      <Navbar />
      <MainContainer>
        <Outlet />
      </MainContainer>
    </>
  );
}

export default App;
