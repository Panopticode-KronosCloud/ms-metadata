-- case insensitive text type
--CREATE EXTENSION IF NOT EXISTS citext;

CREATE TYPE metadata.kind_type    AS ENUM ('file', 'directory');
CREATE TYPE metadata.status_type  AS ENUM ('unavailable', 'deleted', 'staged', 'active');
CREATE TYPE metadata.storage_type AS ENUM ('B2', 'S3', 'disk', 'azure_blob', 'google_cloud_storage');  -- ready for extension

CREATE TABLE metadata.entity (
    id               UUID                    DEFAULT gen_random_uuid() PRIMARY KEY,
    parent_id        UUID                    NULL REFERENCES metadata.entity(id) ON DELETE CASCADE,
    kind             metadata.kind_type      NOT NULL,
    name             TEXT                    NOT NULL,
    extension        TEXT,                   -- NULL for directories
    created          TIMESTAMP               NOT NULL,
    last_modified    TIMESTAMP               NOT NULL,
    size_bytes       BIGINT,
    media_type       TEXT,                   -- "inode/directory" for directories
    custom_metadata  JSONB,
    raw_access       BOOL                    NOT NULL DEFAULT FALSE,
    status           metadata.status_type    NOT NULL,

    CONSTRAINT unique_name UNIQUE NULLS NOT DISTINCT (parent_id, name),

    CHECK (
        (kind = 'directory' AND size_bytes IS NULL AND media_type = 'inode/directory' AND extension IS NULL)
            OR
        (kind = 'file')
        )
);

CREATE INDEX idx_parent_id ON metadata.entity(parent_id);

CREATE TABLE metadata.entity_hash (
    entity_id   UUID   NOT NULL REFERENCES metadata.entity(id) ON DELETE CASCADE,
    algorithm   TEXT   NOT NULL,
    encoding    TEXT   NOT NULL,
    value       TEXT   NOT NULL,

    PRIMARY KEY (entity_id, algorithm)
);

CREATE TABLE metadata.entity_storage (
    entity_id   UUID                    PRIMARY KEY REFERENCES metadata.entity(id) ON DELETE CASCADE,
    storage     metadata.storage_type   NOT NULL,
    reference   TEXT                    NOT NULL
);

CREATE TABLE metadata.entity_thumbnail (
    entity_id   UUID         NOT NULL REFERENCES metadata.entity(id) ON DELETE CASCADE,
    width       INTEGER      NOT NULL,
    height      INTEGER      NOT NULL,

    PRIMARY KEY (entity_id, width, height)
);

CREATE TABLE metadata.entity_consolidation (
    entity_id        UUID          PRIMARY KEY REFERENCES metadata.entity(id) ON DELETE CASCADE,
    version          TEXT          NOT NULL,
    consolidated_at  TIMESTAMP     NOT NULL
);
