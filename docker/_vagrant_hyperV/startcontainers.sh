#docker run -d -p 8000:8000 -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer
#docker run -d -p 3306:3306 -v mysql_data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root mysql
#docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=p0stgr3s -v pg_data:/var/lib/postgresql/data postgres
#docker run -d -p 80:80     -e 'PGADMIN_DEFAULT_EMAIL=user@domain.com' -e 'PGADMIN_DEFAULT_PASSWORD=SuperSecret' dpage/pgadmin4