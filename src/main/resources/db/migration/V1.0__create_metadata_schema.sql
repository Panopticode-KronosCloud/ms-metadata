-- case insensitive text type
--CREATE EXTENSION IF NOT EXISTS citext;

CREATE SCHEMA IF NOT EXISTS metadata;
ALTER SCHEMA metadata OWNER TO metadata_owner;

-- Privileges for app user
GRANT USAGE ON SCHEMA metadata TO metadata_app;
GRANT SELECT, INSERT, UPDATE, DELETE
    ON ALL TABLES IN SCHEMA metadata TO metadata_app;
GRANT USAGE, SELECT
    ON ALL SEQUENCES IN SCHEMA metadata TO metadata_app;

-- Privileges for read-only user
GRANT USAGE ON SCHEMA metadata TO metadata_ro;
GRANT SELECT
    ON ALL TABLES IN SCHEMA metadata TO metadata_ro;