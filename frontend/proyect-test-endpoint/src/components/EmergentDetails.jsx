import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Button, Modal } from 'react-bootstrap';

const EmergentDetails = ({ show, handleClose, id }) => {
    const baseUrl = `http://localhost:8080/api/places/${id}`;
    const [place, setPlace] = useState(null); // Cambia a null para verificar si hay datos
    const [loading, setLoading] = useState(true); // Agrega un estado de carga

    const fetchPlace = () => {
        setLoading(true); // Inicia el estado de carga
        axios.get(baseUrl)
            .then(response => {
                console.log(response.data);
                setPlace(response.data);
            })
            .catch(error => {
                console.error('Error fetching place data:', error);
            })
            .finally(() => {
                setLoading(false); // Finaliza el estado de carga
            });
    };

    useEffect(() => {
        if (id) { // Asegúrate de que id no sea nulo o indefinido
            fetchPlace();
        }
    }, [id]);

    return (
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Detalles del Lugar</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {loading ? ( // Mensaje de carga mientras se obtienen los datos
                    <p>Cargando detalles...</p>
                ) : place ? ( // Muestra los detalles si se obtienen correctamente
                    <>
                        {place.imageUrl && <img src={place.imageUrl} alt={place.name} className="img-fluid" />}
                        <h5>{place.name}</h5>
                        <p><strong>Dirección:</strong> {place.address}</p>
                        <p><strong>Ciudad:</strong> {place.city}</p>
                        <p><strong>Problématica:</strong> {place.problem}</p>
                    </>
                ) : (
                    <p>No se encontraron detalles para este lugar.</p> // Mensaje si no hay datos
                )}
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                    Cerrar
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default EmergentDetails;
