-- case insensitive text type
--CREATE EXTENSION IF NOT EXISTS citext;

CREATE SCHEMA IF NOT EXISTS metadata;
ALTER SCHEMA metadata OWNER TO metadata_owner;

-- Schema access
GRANT USAGE ON SCHEMA metadata TO metadata_app;
GRANT USAGE ON SCHEMA metadata TO metadata_ro;

-- Default privileges (future objects only)
ALTER DEFAULT PRIVILEGES FOR ROLE metadata_owner
IN SCHEMA metadata
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO metadata_app;

ALTER DEFAULT PRIVILEGES FOR ROLE metadata_owner
IN SCHEMA metadata
GRANT SELECT ON TABLES TO metadata_ro;

ALTER DEFAULT PRIVILEGES FOR ROLE metadata_owner
IN SCHEMA metadata
GRANT USAGE, SELECT ON SEQUENCES TO metadata_app;
