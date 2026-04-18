Requirements
Java 17+, Maven, Docker Desktop

Setup & Run
Start the database

bash
docker-compose up -d
Configure database connection
Edit src/main/resources/database.properties:

properties
db.url=jdbc:mysql://localhost:3307/fuel_db
db.user=root
db.password=root
Run the application

bash
mvn clean javafx:run
Database
Port: 3307 (to avoid conflicts)

Auto‑initialized with tables and sample localization strings (EN, FR, JA, FA)

Stop:

bash
docker-compose down -v