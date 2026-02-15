-- Create roles
CREATE ROLE metadata_app   WITH LOGIN PASSWORD 'local-dev-only';
CREATE ROLE metadata_ro    WITH LOGIN PASSWORD 'local-dev-only';

-- Database access
GRANT CONNECT ON DATABASE metadata_microservice_db TO metadata_owner;
GRANT CONNECT ON DATABASE metadata_microservice_db TO metadata_app;
GRANT CONNECT ON DATABASE metadata_microservice_db TO metadata_ro;

-- Ownership
ALTER DATABASE metadata_microservice_db OWNER TO metadata_owner;
