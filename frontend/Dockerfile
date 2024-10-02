# Usa una imagen de Node.js para construir la aplicación
FROM node:14 as build

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos de tu aplicación
COPY package*.json ./
RUN npm install
COPY . .

# Construye la aplicación
RUN npm run build

# Usa Nginx para servir la aplicación
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html

# Expone el puerto 80
EXPOSE 80