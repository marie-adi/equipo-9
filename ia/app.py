from flask import Flask, render_template, request, redirect, url_for
import os
import numpy as np
import cv2
from tensorflow.keras.models import load_model

app = Flask(__name__)

# Cargar el modelo previamente entrenado
model = load_model('accessibility_model.keras')

# Definir el tamaño de las imágenes
IMG_HEIGHT, IMG_WIDTH = 224, 224

# Función para preprocesar la imagen
def preprocess_image(image_path):
    img = cv2.imread(image_path)
    img = cv2.resize(img, (IMG_WIDTH, IMG_HEIGHT))
    img = img / 255.0  # Normalizar a [0, 1]
    img = np.expand_dims(img, axis=0)  # Añadir batch dimension
    return img

# Ruta para la página de carga
@app.route('/', methods=['GET', 'POST'])
def upload_file():
    if request.method == 'POST':
        if 'file' not in request.files:
            return redirect(request.url)
        
        file = request.files['file']
        if file.filename == '':
            return redirect(request.url)
        
        if file:
            # Guardar la imagen cargada
            filepath = os.path.join('uploads', file.filename)
            file.save(filepath)
            
            # Preprocesar la imagen
            img = preprocess_image(filepath)
            
            # Realizar predicción
            prediction = model.predict(img)
            predicted_class = int(np.round(prediction[0][0]))
            
            # Mapear el valor predicho a "accesible" o "no accesible"
            if predicted_class == 1:
                result = "accesible"
            else:
                result = "no accesible"
            
            # Eliminar la imagen procesada
            os.remove(filepath)
            
            # Mostrar el resultado
            return render_template('result.html', predicted_class=result)
    
    return render_template('upload.html')

# Ejecutar la aplicación Flask
if __name__ == '__main__':
    # Asegúrate de que exista el directorio de carga
    if not os.path.exists('uploads'):
        os.makedirs('uploads')
    app.run(debug=True)
