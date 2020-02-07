COPY COUNTRIES FROM 'classpath:db/migration/countries.csv' DELIMITER ',' CSV HEADER;
COPY CITIES FROM 'classpath:db/migration/cities.csv' DELIMITER ',' CSV HEADER;