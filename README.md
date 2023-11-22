# AD-Manejo-de-Ficheros
Realizar lectura y escritura de ficheros XLS, CSV, JSON y XML con DOM en Java.
---
### Integrantes del proyecto:
- **Rubén Alfonso Gonzalo**
- **Noel Prieto Pardo**
- **Luis Fernández Castelo** 
---
### Estructura del proyecto:
```mermaid
graph LR
A[Acceso.jsp] --> B[ServletAcceso.java] 
B --> C{Error.jsp}
B --> D[DatosAbiertosCSV.jsp]
B --> E[DatosAbiertosJSON.jsp]
B --> F[DatosAbiertosXML.jsp]
B --> G[DatosAbiertosXLS.jsp]
