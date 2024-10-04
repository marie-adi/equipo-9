![+inclusion](https://github.com/user-attachments/assets/afce4280-640d-4e24-84c4-c56ed295c00f)


## Descripción del Proyecto

**Más Inclusión** 
es una aplicación web que aborda la problemática de la accesibilidad y la inclusión social, alineándose con el Objetivo de Desarrollo Sostenible (ODS) 11: Ciudades Sostenibles. La aplicación permite a los usuarios reportar y compartir información sobre lugares que presentan barreras de acceso para personas con discapacidades. 

Los usuarios pueden subir imágenes y clasificar espacios como accesibles o no accesibles, generando una base de datos que promueve la conciencia social y mejora los espacios públicos. A través de una interfaz intuitiva y un diseño minimalista, la aplicación facilita la colaboración y el acceso a información relevante, contribuyendo al desarrollo de entornos más inclusivos y accesibles.

---
## Tecnologías Utilizadas

- **Frontend:** React, JavaScript, CSS, Bootstrap
- **Backend:** Java, Python
- **Base de Datos:** MySQL
- **Librerías:** NumPy, Pandas, TensorFlow
- **Modelo:** Sequential, YOLO8n

---
## Diseño
### Prototipos de baja fidelidad
![image](https://github.com/user-attachments/assets/8aaca556-bd3d-4e41-877f-c2ded0d31ddd)

### Design System
![image](https://github.com/user-attachments/assets/ab11aeed-acd6-4d5b-8b8d-db9fb1e77763)

### Prototipos finales
![image](https://github.com/user-attachments/assets/c8e8da7d-f399-4815-81f2-00885ecd377f)


## Front End

### 1. Página principal
![image](https://github.com/user-attachments/assets/ba16c422-af53-40b7-9dde-4694044ef14f)

### 2. Galería de imágenes
![image](https://github.com/user-attachments/assets/b6eafec7-cb39-400e-b4fc-28947951299d)

### 3. Formulario emerjente para registrar un nuevo lugar
![image](https://github.com/user-attachments/assets/53c80446-a4aa-442d-9685-fa0f7112f392)

### 4. Notificación al usuario
![image](https://github.com/user-attachments/assets/aa9072ca-58f4-4ce4-8280-0df062cd9c19)


### 5. Pantalla detalle emergente
![image](https://github.com/user-attachments/assets/b539060c-b1ac-45ee-a1c5-a196e10f4cc4)

---

## Backend

### Estructura de carpetas
La aplicación sigue un diseño por capas, organizado de la siguiente manera:

- **`src`**: Contiene el código fuente de la aplicación.
- **`java/com/inclusivo/application`**: Agrupa la lógica de la aplicación en módulos específicos.
    - **`places`**: Módulo relacionado con "lugares".
        - **`application`**:
            - **`controllers`**: Maneja las solicitudes HTTP y el flujo de la aplicación.
            - **`converters`**: Convierte datos entre diferentes representaciones.
            - **`facade`**: Intermediario para operaciones de servicios.
            - **`services`**: Contiene la lógica del negocio.
        - **`domain`**: Define el modelo del dominio y las entidades (ej. `PlaceDTO.java`, `PlaceModel.java`).
        - **`exceptions`**: Maneja excepciones específicas (ej. `PlaceDeleteException.java`).
        - **`infrastructure`**:
            - **`configuration`**: Configuraciones generales de la aplicación.
            - **`repository`**: Interactúa con la base de datos.
            - **`users`**: Gestión de usuarios.
- **`resources`**: Archivos de configuración y recursos no Java.
- **`test`**: Contiene pruebas unitarias y de integración.

### Beneficios de la Estructura por Capas

1. **Separación de preocupaciones**: Cada capa tiene responsabilidades claras, facilitando el entendimiento y mantenimiento del código.
2. **Escalabilidad**: Permite agregar nuevas funcionalidades y módulos sin afectar las capas existentes.
3. **Reusabilidad**: Componentes y clases pueden ser reutilizados en diferentes partes de la aplicación.
4. **Facilidad de prueba**: Facilita la realización de pruebas unitarias e integración debido a la organización modular.
5. **Mantenimiento simplificado**: Cambios en un componente no afectan el resto del sistema, gracias a la separación de capas.
6. **Interoperabilidad**: Permite el uso de diferentes tecnologías sin modificaciones drásticas en toda la aplicación.

![Captura desde 2024-10-04 04-37-30](https://github.com/user-attachments/assets/63fa8a65-255a-4936-b363-820d99be9993)

### Estructura base de datos
### Estructura de la Tabla `places`

La tabla `places` está diseñada para almacenar información sobre diferentes lugares y sus respectivas problemáticas urbanas. A continuación, se describen las columnas de la tabla:

- **`id BIGINT AUTO_INCREMENT PRIMARY KEY`**:
    
    - Identificador único para cada registro en la tabla. Utiliza el tipo de dato `BIGINT` para manejar un amplio rango de valores. `AUTO_INCREMENT` permite que el valor se incremente automáticamente para cada nuevo registro, garantizando que cada lugar tenga un ID único.
- **`name VARCHAR(255)`**:
    
    - Almacena el nombre del lugar (por ejemplo, "Biblioteca Nacional de España"). El tipo de dato `VARCHAR(255)` permite almacenar cadenas de texto de hasta 255 caracteres.
- **`city VARCHAR(255)`**:
    
    - Guarda el nombre de la ciudad donde se encuentra el lugar. También utiliza el tipo de dato `VARCHAR(255)`.
- **`address VARCHAR(255)`**:
    
    - Almacena la dirección específica del lugar. Este campo permite hasta 255 caracteres.
- **`problem VARCHAR(255)`**:
    
    - Describe el problema relacionado con la movilidad reducida en el lugar. Este campo también permite una longitud de hasta 255 caracteres.
- **`ranking INT`**:
    
    - Almacena un valor numérico que representa la clasificación del lugar en función de la gravedad del problema o de la calidad de la accesibilidad. El tipo de dato `INT` se utiliza para manejar números enteros.
- **`image_url VARCHAR(255)`**:
    
    - Guarda la URL de una imagen relacionada con el lugar, permitiendo así la visualización de recursos gráficos. Este campo puede almacenar hasta 255 caracteres.

### Consideraciones

- **Normalización**: La tabla está diseñada para contener datos específicos y relacionados con cada lugar, lo que puede facilitar la normalización de la base de datos en el futuro si se requiere agregar más tablas (por ejemplo, para usuarios o comentarios).
    
- **Escalabilidad**: La estructura actual permite la adición de nuevos lugares y la expansión de sus atributos en caso de que se desee incluir más información sobre cada lugar.

![Captura desde 2024-10-04 04-43-37](https://github.com/user-attachments/assets/65b40707-1d46-4d4b-a5db-5e02c2c830d4)

### Api Restful
### 1. Obtener todos los lugares

- **Método**: `GET`
- **Ruta**: `/api/places`
- **Descripción**: Recupera una lista de todos los lugares almacenados.
- **Respuesta exitosa**:
    - **Código**: `200 OK`
    - **Cuerpo**: `List<PlaceDTO>` (una lista de objetos `PlaceDTO`).
- **Respuesta en caso de error**:
    - **Código**: `404 Not Found`
    - **Mensaje**: "La lista se encuentra vacía" (cuando no hay lugares disponibles).

### 2. Obtener un lugar por ID

- **Método**: `GET`
- **Ruta**: `/api/places/id/{id}`
- **Parámetros**:
    - **`id`**: Identificador único del lugar a recuperar (tipo: `int`).
- **Descripción**: Recupera un lugar específico utilizando su ID.
- **Respuesta exitosa**:
    - **Código**: `200 OK`
    - **Cuerpo**: `PlaceDTO` (objeto que representa el lugar).
- **Respuesta en caso de error**:
    - **Código**: `404 Not Found` (si no se encuentra el lugar).

### 3. Crear un nuevo lugar

- **Método**: `POST`
- **Ruta**: `/api/places`
- **Cuerpo de la solicitud**:
    - `PlaceDTO` (objeto que contiene la información del nuevo lugar).
- **Descripción**: Crea un nuevo lugar en la base de datos.
- **Respuesta exitosa**:
    - **Código**: `201 Created`
    - **Cuerpo**: `PlaceDTO` (objeto que representa el lugar creado).

### 4. Eliminar un lugar por ID

- **Método**: `DELETE`
- **Ruta**: `/api/places/{id}`
- **Parámetros**:
    - **`id`**: Identificador único del lugar a eliminar (tipo: `int`).
- **Descripción**: Elimina un lugar específico utilizando su ID.
- **Respuesta exitosa**:
    - **Código**: `200 OK`
    - **Cuerpo**: `Integer` (el ID del lugar eliminado).
- **Respuesta en caso de error**:
    - **Código**: `404 Not Found` (si no se encuentra el lugar).

### 5. Obtener lugares por ciudad

- **Método**: `GET`
- **Ruta**: `/api/places/city/{city}`
- **Parámetros**:
    - **`city`**: Nombre de la ciudad para filtrar los lugares (tipo: `String`).
- **Descripción**: Recupera una lista de lugares filtrados por ciudad.
- **Respuesta exitosa**:
    - **Código**: `200 OK`
    - **Cuerpo**: `List<PlaceDTO>` (una lista de objetos `PlaceDTO`).
- **Respuesta en caso de error**:
    - **Código**: `404 Not Found`
    - **Mensaje**: "No se han encontrado resultados con la ciudad {nombre de la ciudad}" (cuando no hay lugares en la ciudad especificada).

### 6. Obtener un lugar por nombre

- **Método**: `GET`
- **Ruta**: `/api/places/name/{name}`
- **Parámetros**:
    - **`name`**: Nombre del lugar a buscar (tipo: `String`).
- **Descripción**: Recupera un lugar específico utilizando su nombre.
- **Respuesta exitosa**:
    - **Código**: `200 OK`
    - **Cuerpo**: `PlaceDTO` (objeto que representa el lugar).
- **Respuesta en caso de error**:
    - **Código**: `404 Not Found` (si no se encuentra el lugar).

### Pruebas Unitarios realizadas con Mockito
La clase `ServiceImplTest` contiene pruebas unitarias para el servicio `PlaceServiceImpl`, las cuales están diseñadas para verificar el comportamiento de métodos individuales, como `findAll()`, `findById()`, `save()`, y `deleteById()`. Las pruebas unitarias se centran en validar la lógica interna de cada método de forma aislada, asegurando que funcionen correctamente sin depender de otros componentes del sistema. En este contexto, se utiliza **Mockito** para crear mocks del `PlaceRepository`, lo que permite simular su comportamiento y probar la lógica del servicio sin acceder a la base de datos real. Esto facilita la detección temprana de errores y asegura la robustez del servicio, garantizando que maneje adecuadamente excepciones como `PlaceDeleteException` e `IllegalArgumentException`, y que realice correctamente operaciones como la creación, búsqueda y eliminación de lugares.

---

## Equipo

- **Carolina Tomás**
- **Sandra Esteban**
- **Mariela Adimari**
- **Iryna Bilokon**
- **Scarlet Gonzalez**
- **Silvia Piñel**
- **Sara Alcaraz**
