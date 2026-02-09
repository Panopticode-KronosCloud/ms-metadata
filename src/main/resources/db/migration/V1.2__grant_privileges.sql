-- case insensitive text type
--CREATE EXTENSION IF NOT EXISTS citext;

-- Ensure future tables behave correctly
ALTER DEFAULT PRIVILEGES FOR ROLE metadata_owner
IN SCHEMA metadata
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO metadata_app;

ALTER DEFAULT PRIVILEGES FOR ROLE metadata_owner
IN SCHEMA metadata
GRANT SELECT ON TABLES TO metadata_ro;

ALTER DEFAULT PRIVILEGES FOR ROLE metadata_owner
IN SCHEMA metadata
GRANT USAGE, SELECT ON SEQUENCES TO metadata_app;

ALTER DEFAULT PRIVILEGES FOR ROLE metadata_owner
IN SCHEMA metadata
GRANT SELECT ON SEQUENCES TO metadata_ro;
