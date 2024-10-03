from ultralytics import YOLO

model = YOLO('yolov8n.pt')

model.train(data='data.yaml', epochs=100, imgsz=224)

model.val(data='data.yaml')

model.export(format='onnx')  # Export to ONNX format

