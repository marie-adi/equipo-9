import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import React from 'react';
import Nav from 'react-bootstrap/Nav';

const NavigationBar = () => {
    return (
        <div className="px-5">
            <Nav
                activeKey=""
                onSelect={(selectedKey) => alert(`selected ${selectedKey}`)}
                className='p-3 px-5 d-flex justify-content-between'
            >
                <Nav.Item>
                    <Link to="" className="nav-link">
                        <img src='src/assets/LogoMasInclusionGrande.webp' style={{ width: '180px', height: 'auto' }} alt="Logo" />
                    </Link>
                </Nav.Item>
                <Nav.Item className='mt-4'>
                    <Button variant="links">Registrarse</Button>
                    <Button variant="dark" className="ml-3">Inicio de sesión</Button> {/* Cambié ml-5 a ml-3 para mayor separación */}
                </Nav.Item>
            </Nav>
        </div>
    );
}

export default NavigationBar;