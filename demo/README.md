# Spring demo

```
docker run --name spring_demo -e POSTGRES_PASSWORD=admin -e POSTGRES_USER=admin -e POSTGRES_DB=spring_demo -p 5432:5432 -d postgres
```

```
docker exec spring_demo psql -U admin -d spring_demo
```