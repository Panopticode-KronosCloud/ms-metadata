-- case insensitive text type
--CREATE EXTENSION IF NOT EXISTS citext;

CREATE SCHEMA metadata;

CREATE TYPE metadata.kind_type    AS ENUM ('file', 'directory');
CREATE TYPE metadata.status_type  AS ENUM ('unavailable', 'deleted', 'staged', 'ready');
CREATE TYPE metadata.storage_type AS ENUM ('b2');  -- ready for extension

CREATE TABLE metadata.entity (
                                 id             UUID                    DEFAULT gen_random_uuid() PRIMARY KEY,
                                 parent_id      UUID                    NULL REFERENCES metadata.entity(id) ON DELETE CASCADE,
                                 kind           metadata.kind_type      NOT NULL,
                                 blob_storage   metadata.storage_type,
                                 blob_ref       TEXT,
                                 name           TEXT                    NOT NULL,
                                 created        TIMESTAMP               NOT NULL,
                                 last_modified  TIMESTAMP               NOT NULL,
                                 size_b         BIGINT,
                                 media_type     TEXT,                   -- "inode/directory" for directories
                                 metadata       JSONB,
                                 hash_sha3_256  TEXT,                   -- NULL for directories
                                 thumbnail      TEXT,
                                 consolidate_v  TEXT,                   -- NULL for directories
                                 raw_access     BOOL                    NOT NULL DEFAULT FALSE,
                                 status         metadata.status_type    NOT NULL,

                                 CONSTRAINT unique_name UNIQUE(parent_id, name)
);

CREATE INDEX idx_parent_id          ON metadata.entity(parent_id);
CREATE INDEX idx_blob_storage_type  ON metadata.entity(blob_storage);