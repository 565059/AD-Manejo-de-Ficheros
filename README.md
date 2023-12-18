# AD-Manejo-de-Ficheros
Realizar lectura y escritura de ficheros XLS, CSV, JSON y XML con DOM en Java.
---
### Estructura del proyecto:
```mermaid
graph
A(Acceso.jsp) --> B[ServletAcceso.java]
B --> D[ConexionCSV.java]
B --> E[ConexionJSON.java]
B --> F[ConexionXLS.java]
B --> G[ConexionXML.java]
D --> H{Excepcion}
E --> H
F --> H
G --> H
H -- Si --> Error.jsp
H -- No --> Resultados.jsp
```
---
### Integrantes del proyecto:
- **Rubén Alfonso Gonzalo**
- **Noel Prieto Pardo**
- **Adrián del Bosque Vicente**
- **Luis Fernández Castelo**
---
Reparto de las tareas: [Cronograma Jira](https://luisfernandezlf70.atlassian.net/jira/software/projects/AD/boards/1/timeline?shared=&atlOrigin=eyJpIjoiZTg4NGMyZDExNDBiNDgwOTkzNjZjYTFmYTZlOGVkYzgiLCJwIjoiaiJ9)
<br>
![Cronograma-AD-Complejo](https://github.com/565059/AD-Manejo-de-Ficheros/assets/118855900/7aad9b5e-cbe6-4eb0-b50d-073c99d18a15)


Página de datos abiertos utilizados: [Desfibriladores en Alcobendas](https://datos.gob.es/es/catalogo/l01280066-desfibriladores-en-alcobendas1)
