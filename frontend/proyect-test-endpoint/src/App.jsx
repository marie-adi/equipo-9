import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import PlaceList from './components/PlaceList';
import './App.css';
import NavigationBar from './components/NavigationBar';
import Button from 'react-bootstrap/Button';
import EmergentForm from './components/EmergentForm';
import bannerImage from '/src/assets/baner-image.jpg';

function App() {
  const [showForm, setShowForm] = useState(false);

  const handleOpenForm = () => setShowForm(true);
  const handleCloseForm = () => setShowForm(false);

  return (
    <Router>
      <NavigationBar />
      <div 
        className="container-fluid rounded position-relative" 
        style={{ 
          height: 'auto',
          width: '75%',
          padding: '10% 0', // Espaciado interno
          overflow: 'hidden' // Asegúrate de que el pseudo-elemento no se salga
        }}
      >
        {/* Pseudo-elemento para la imagen de fondo */}
        <div 
          style={{ 
            position: 'absolute', 
            top: 0, 
            left: 0, 
            right: 0, 
            bottom: 0, 
            backgroundImage: `url(${bannerImage})`, // Imagen de fondo
            backgroundSize: 'cover', // Asegúrate de cubrir todo el contenedor
            backgroundPosition: 'center', // Centra la imagen
            zIndex: 1 // Asegúrate de que esté por debajo del texto
          }} 
        />
        
        {/* Capa para oscurecer la imagen */}
        <div 
          style={{ 
            position: 'absolute', 
            top: 0, 
            left: 0, 
            right: 0, 
            bottom: 0, 
            backgroundColor: 'rgba(0, 0, 0, 0.5)', // Capa negra con opacidad
            zIndex: 2 // Asegúrate de que esté por encima de la imagen
          }} 
        />
        
        <div className="d-flex flex-column align-items-center justify-content-center text-center position-relative" style={{ height: '100%', zIndex: 3 }}>
          <h1 className="text-white">Más Inclusión: La aplicación que te llevará a donde quieras.</h1>
          <h3 className="text-white mt-4">¡No dejes que las barreras te paren!</h3> {/* Espacio entre los textos */}

          {/* Botón centrado y ajustado con tamaño */}
          <Button
            variant="dark"
            onClick={handleOpenForm}
            style={{
              width: '200px',
              height: '50px',
              marginTop: '30px'  // Espacio adicional entre el segundo texto y el botón
            }}
          >
            Registra tu problema
          </Button>
        </div>
      </div>
      <div className="mt-4"> {/* Margen superior para separar del siguiente componente */}
        {/* Renderizar el formulario emergente pasando show y handleClose como props */}
        <EmergentForm show={showForm} handleClose={handleCloseForm} />
      </div>
      
      <Routes>
        <Route path="/" element={<PlaceList />} /> {/* Ruta principal */}
      </Routes>
    </Router>
  );
}

export default App;

