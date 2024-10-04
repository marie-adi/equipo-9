import React, { useState } from 'react';
import axios from 'axios';
import swal from 'sweetalert';
import { Button, Modal, Form } from 'react-bootstrap';

const EmergentForm = ({ show, handleClose }) => {
  const [name, setName] = useState('');
  const [address, setAddress] = useState('');
  const [problem, setProblem] = useState('');
  const [ranking, setRanking] = useState(0);
  const [city, setCity] = useState('Madrid');
  const [imageUrl, setImageUrl] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault(); // Previene el comportamiento predeterminado
    console.log('handleSubmit llamado');

    // Verifica que los estados están actualizados
    console.log('Valores actuales: ', { name, city, address, problem, ranking, imageUrl });

    const local = "http://localhost:8080/api/places";
    
    const placeDTO = {
      name,
      city,
      address,
      problem,
      ranking,
      imageUrl
    };

    console.log("Datos del DTO antes de enviar:", placeDTO);

    try {
      const response = await axios.post(local, placeDTO);
      console.log('Respuesta de la API:', response.data);
      if (response.status === 201) {
        swal("¡Registro creado exitosamente!", {
          icon: "success",
        }).then(() => {
          handleClose();
          window.location.reload(); // Recargar la página
        });
      }
    } catch (error) {
      console.error('Error al crear el registro:', error);
      if (error.response) {
        console.error('Error de respuesta del servidor:', error.response.data);
      }
      swal("Error al crear el registro", {
        icon: "error",
      });
    }
  };

  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>Registro de incidencia</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form onSubmit={handleSubmit} className='p-4'>
          <Form.Group className="mb-3">
            <Form.Label htmlFor="nameInput">Nombre del Lugar</Form.Label>
            <Form.Control
              type="text"
              id="nameInput"
              value={name}
              onChange={(e) => setName(e.target.value)}
              placeholder="Ingresa el nombre del lugar"
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label htmlFor="addressInput">Dirección</Form.Label>
            <Form.Control
              type="text"
              id="addressInput"
              value={address}
              onChange={(e) => setAddress(e.target.value)}
              placeholder="c/ejemplo 12, 1234"
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label htmlFor="cityInput">Ciudad</Form.Label>
            <Form.Control
              type="text"
              value={city}
              onChange={(e) => setCity(e.target.value)} 
              placeholder="Madrid"
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label htmlFor="problemInput">Probelmática</Form.Label>
            <Form.Control
              type="text"
              id="problemInput"
              value={problem}
              onChange={(e) => setProblem(e.target.value)}
              placeholder="Describa brevemente el problema que se ha encontrado"
            />
          </Form.Group>

          <Form.Group className="mb-3">
            <Form.Label>Imagen</Form.Label>
            <Form.Control type="file" onChange={(e) => setImageUrl(e.target.files[0].name)} />
          </Form.Group>

          <Button className="mt-3" variant="success" type="submit">
            Enviar
          </Button>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Cancelar
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default EmergentForm;
