# Ecommerce_App_Spring_Boot

- Run docker ps to list all running containers.
- Find the name or ID of the PostgreSQL container in the output.
- Run **docker exec -it <container_name_or_id> bash**, replacing <container_name_or_id> with the actual name or ID of your PostgreSQL container.
- Inside the container's bash session, run **psql -U postgres -d ecommerce_db** to connect to the PostgreSQL server and then DB.
- Run **\l** to list all available databases.
- **\dt** list of relations
- **\d table_name** schema of a specific table
- Run **\q** to exit the psql prompt.
- Press **ctrl+D** to exit the bash session inside the container.