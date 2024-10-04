// src/components/PlaceList.jsx
import React, { useEffect, useState } from 'react';
import 'bootstrap-icons/font/bootstrap-icons.css';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { Card, Button, Row, Col, Container } from 'react-bootstrap';
import swal from 'sweetalert';

const PlaceList = () => {
    const baseUrl = "http://localhost:8080/api/places";
    const [places, setPlaces] = useState([]);

    const fetchPlaces = () => {
        axios.get(baseUrl)
            .then(response => {
                console.log(response.data);
                setPlaces(response.data);
            })
            .catch(error => {
                console.error('Error fetching places data:', error);
            });
    };

    useEffect(() => {
        fetchPlaces();
    }, []);

    const removePlace = (id) => {
        console.log("Removing place " + id);
        swal({
            title: "¿Estas seguro de eliminar el registro?",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then(async (willDelete) => {
                if (willDelete) {
                    await deletePlace(id);
                    swal("Your imaginary file has been deleted!", {
                        icon: "success",
                    });
                } else {
                    swal("Your imaginary file is safe!");
                }
            });
    };

    const deletePlace = async (id) => {
        try {
            const response = await axios.delete(`${URL}/${id}`);
            return response.data

        } catch (error) {
            console.error('Error al borrar la imagen:', error);
            throw error;
        }
    }

    const showDetailsInAlert = (place) => {
        swal({
            title: place.name,
            text: `
                Dirección: ${place.address}
                Ciudad: ${place.city}
                Puntuación: ${place.ranking}
                Problématica: ${place.problem}
            `,
            icon: place.imageUrl, // Puedes usar la URL de la imagen como icono
            buttons: {
                cancel: "Cerrar",
            },
        });
    };

    return (
        <Container className='mt-3'>
            {places.length === 0 ? (
                <p>No hay lugares disponibles.</p>
            ) : (
                <Row>
                    {places.map(place => (
                        <Col key={place.id} md={3} xs={6} className='mt-4'>
                            <Card className="d-flex m-2" style={{ width: '300px', height: '400px' }} onClick={() => showDetailsInAlert(place)}> 
                                <Card.Img variant="top" src={place.imageUrl} style={{ height: '200px', objectFit: 'cover' }} /> 
                                <Card.Body className='mt-3 d-flex flex-column justify-content-between'>
                                    <div className="d-flex align-items-center">
                                        <Card.Title className="mb-0 me-2" style={{ flex: 1, minWidth: '0' }}> 
                                            {place.name}
                                        </Card.Title>
                                        <i className="bi bi-star ms-3" style={{ fontSize: '24px', color: 'gold' }}></i>
                                        <span className="ms-1">{place.ranking}</span> 
                                    </div>
                                    <Card.Text className="mt-2" style={{ flex: 1 }}> 
                                        {place.problem}
                                    </Card.Text>
                                </Card.Body>
                                <Card.Footer className='mt-2 d-flex justify-content-between'>
                                    <Button variant="dark" onClick={() => removePlace(place.id)}>Eliminar</Button>
                                    <Button variant="light">Actualizar</Button>
                                </Card.Footer>
                            </Card>
                        </Col>
                    ))}
                </Row>
            )}
        </Container>
    );

};

export default PlaceList;


