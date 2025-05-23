User Management App, built with:

- **Spring Boot** 
- **Kafka** 
- **WebSocket** 
- **MongoDB** 
- **Vue 3 + Vuex + Tailwind CSS** 
- **Docker**

---

## API Endpoints

Base URL: `http://localhost:8086/api/users`

### `GET /`
Fetch all users.

### `POST /`
Add a user.

**Example body:**
```json
{
  "firstName": "Alice",
  "lastName": "Smith",
  "email": "alice@example.com",
  "password": "secret",
  "action": "ADD"
}
```
### `POST /delete`
Delete a user.

**Example body:**
```json
{
  "firstName": "Alice",
  "lastName": "Smith",
  "email": "alice@example.com",
  "password": "secret",
  "action": "DELETE"
}
```
WebSocket updates are sent to ws://localhost:8086/ws
Spring Boot backend on http://localhost:8086
Frontend on http://localhost:8080
MongoDB on port 27017

## How to Run
docker-compose up -d
mvn clean install
Run the backend app - it serves as both the producer and the consumer 

On frontend - https://github.com/mariaost254/project-front
Create a .env file

```env
VITE_API_BASE=http://localhost:8086/api/users
VITE_SOCKET_ENDPOINT=http://localhost:8086/ws
```
npm install
npm run dev
