## Overview
This project is a RESTful API for managing Machine Learning Workspaces, Models, and Evaluation Metrics using Jakarta REST (JAX-RS).
### Features
- Create, retrieve and delete ML Workspaces
- Create and list Machine Learning Models
- Filter models by status
- Add and retrieve Evaluation Metrics for models
- Prevent deletion of workspaces that contain models
- Prevent adding metrics to deprecated models
- Root discovery endpoint providing API metadata
## API Design

### Resources

| Resource | Endpoint | Description |
|----------|----------|-------------|
| Discovery | GET / | API information |
| Workspaces | GET /workspaces | List all workspaces |
| Workspaces | POST /workspaces | Create a workspace |
| Workspace | GET /workspaces/{id} | Get a workspace |
| Workspace | DELETE /workspaces/{id} | Delete a workspace |
| Models | GET /models | List all models |
| Models | GET /models?status=ACTIVE | Filter models by status |
| Models | POST /models | Create a model |
| Metrics | GET /models/{modelId}/metrics | List metrics |
| Metrics | POST /models/{modelId}/metrics | Add evaluation metric |

---

## Project Structure

```
src
 └── main
     └── java
         └── com.mlops
             ├── model
             ├── resource
             ├── exception
             ├── store
             └── ApplicationConfig.java
```

---

# Build Instructions

## Prerequisites

- Java 17 (or your project's required version)
- Maven 3.9+
- Git

---

## Clone the repository

```bash
git clone https://github.com/<your-username>/mlops-api.git
```

```bash
cd mlops-api
```

---

## Build the project

```bash
mvn clean install
```

---

## Run the server

If using Grizzly:

```bash
mvn exec:java
```

If deploying to Tomcat:

```bash
mvn package
```

Deploy the generated WAR file to your Tomcat server.

---

The API will be available at

```
http://localhost:8080/mlops-api/
```

*(Replace with your actual base URL if different.)*

---

# Sample cURL Commands

## 1. Get API Information

```bash
curl -X GET http://localhost:8080/mlops-api/
```

Example Response

```json
{
  "version":"v1",
  "admin-name":"Nisali Nawarathne",
  "admin_mail":"nisali@mlops.com",
  "contact":"0777123456",
  "country":"Sri-Lanka"
}
```

---

## 2. Create a Workspace

```bash
curl -X POST http://localhost:8080/mlops-api/workspaces \
-H "Content-Type: application/json" \
-d '{
    "name":"Fraud Detection",
    "description":"Workspace for fraud detection models"
}'
```

Response

```json
{
  "id":"WS-xxxxxxxx",
  "name":"Fraud Detection",
  "description":"Workspace for fraud detection models",
  "modelIds":[]
}
```

---

## 3. Get All Workspaces

```bash
curl -X GET http://localhost:8080/mlops-api/workspaces
```

---

## 4. Create a Machine Learning Model

Replace the workspace ID with one returned from the previous request.

```bash
curl -X POST http://localhost:8080/mlops-api/models \
-H "Content-Type: application/json" \
-d '{
    "name":"Random Forest",
    "status":"ACTIVE",
    "workspaceId":"WS-xxxxxxxx"
}'
```

---

## 5. Get All Models

```bash
curl -X GET http://localhost:8080/mlops-api/models
```

---

## 6. Filter Models by Status

```bash
curl -X GET "http://localhost:8080/mlops-api/models?status=ACTIVE"
```

---

## 7. Add an Evaluation Metric

Replace the model ID with the ID returned when creating a model.

```bash
curl -X POST http://localhost:8080/mlops-api/models/MOD-xxxxxxxx/metrics \
-H "Content-Type: application/json" \
-d '{
    "accuracyScore":0.96,
    "precision":0.94,
    "recall":0.91
}'
```

---

## 8. Get Evaluation Metrics

```bash
curl -X GET http://localhost:8080/mlops-api/models/MOD-xxxxxxxx/metrics
```

---

## 9. Delete a Workspace

```bash
curl -X DELETE http://localhost:8080/mlops-api/workspaces/WS-xxxxxxxx
```

If the workspace still contains models, the API returns an error (`WorkspaceNotEmptyException`).

---

# Error Handling

The API returns appropriate HTTP status codes.

| Status Code | Meaning |
|-------------|----------|
| 200 | Success |
| 201 | Resource Created |
| 204 | Successfully Deleted |
| 400 | Bad Request |
| 404 | Resource Not Found |
| 409 | Business Rule Violation |
| 500 | Internal Server Error |

Business exceptions include:

- WorkspaceNotEmptyException
- LinkedWorkspaceNotFoundException
- ModelDeprecatedException

---

# Technologies Used

- Java
- Jakarta RESTful Web Services (JAX-RS)
- Maven
- Grizzly/Tomcat
- JSON

---

# Author

**Nisali Nawarathne**

Email: nisali@mlops.com
