FROM mysql:8.0

COPY database/schema.sql /docker-entrypoint-initdb.d/01-schema.sql
COPY database/localization_data.sql /docker-entrypoint-initdb.d/02-data.sql

EXPOSE 3306