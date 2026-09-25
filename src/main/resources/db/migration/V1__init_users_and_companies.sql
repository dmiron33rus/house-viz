CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE companies (
                           id          UUID PRIMARY KEY,
                           name        VARCHAR(255) NOT NULL,
                           logo_url    VARCHAR(500),
                           phone       VARCHAR(50),
                           email       VARCHAR(255),
                           address     VARCHAR(500),
                           active      BOOLEAN NOT NULL DEFAULT TRUE,
                           created_at  TIMESTAMPTZ NOT NULL,
                           updated_at  TIMESTAMPTZ NOT NULL,
                           version     BIGINT
);

CREATE TABLE users (
                       id              UUID PRIMARY KEY,
                       email           VARCHAR(255) NOT NULL UNIQUE,
                       password_hash   VARCHAR(255) NOT NULL,
                       full_name       VARCHAR(255) NOT NULL,
                       phone           VARCHAR(50),
                       role            VARCHAR(30) NOT NULL,
                       active          BOOLEAN NOT NULL DEFAULT TRUE,
                       company_id      UUID REFERENCES companies(id),
                       created_at      TIMESTAMPTZ NOT NULL,
                       updated_at      TIMESTAMPTZ NOT NULL,
                       version         BIGINT
);

CREATE INDEX idx_users_company ON users(company_id);
CREATE INDEX idx_users_email ON users(email);