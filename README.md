#CINEMA OF CLASSIC MOVIES
by Livia Kerr

##Run CINEMA from the command line

Start Postgresql using Docker first:

```docker
docker run -ti -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword postgres
```
Before you execute:
```
mvn clean install
```

Or:
```
mvn clean install -DskipTests
```

Now execute the application using maven:
```bash
cd application
mvn spring-boot:run
```

Too see the results in your browser, change to ...
```bash
cd frontend
cd cinema
```

... and run:
```bash
npm start
```
