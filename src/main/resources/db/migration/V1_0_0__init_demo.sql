-- DROP TABLE IF EXISTS Asset;
-- DROP TABLE IF EXISTS Person;
-- DROP TABLE IF EXISTS Possessor;
-- DROP TABLE IF EXISTS Worth;
-- DROP TABLE IF EXISTS Kit_relation;
-- DROP TABLE IF EXISTS Address;
-- DROP TABLE IF EXISTS Classification;
-- DROP TABLE IF EXISTS Comment;
-- DROP TABLE IF EXISTS Description;


CREATE TABLE IF NOT EXISTS Person (
    id SERIAL PRIMARY KEY,
    azure_id VARCHAR(500) UNIQUE NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Possessor (
    id SERIAL PRIMARY KEY,
    structural_unit SMALLINT,
    subdivision SMALLINT
);

CREATE TABLE IF NOT EXISTS Classification (
    sub_class VARCHAR(20) PRIMARY KEY,
    main_class VARCHAR(20) NOT NULL
);

CREATE OR REPLACE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  NEW.modified_at = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TABLE IF NOT EXISTS Asset (
    id VARCHAR(20) PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL,
    sub_class VARCHAR(30) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    user_id INT DEFAULT NULL REFERENCES Person(id) ON DELETE SET NULL ON UPDATE CASCADE,
    possessor_id INT NOT NULL REFERENCES Possessor(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    expiration_date DATE,
    delicate_condition BOOLEAN NOT NULL DEFAULT FALSE,
    checked BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    price NUMERIC(12, 2),
    residual_price NUMERIC(12, 2),
    purchase_date TIMESTAMP,
    building_abbreviature VARCHAR(10) NOT NULL,
    room VARCHAR(10),
    description VARCHAR(255)
);

CREATE TRIGGER set_timestamp
BEFORE UPDATE ON Asset
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();


CREATE TABLE IF NOT EXISTS Kit_relation (
    component_asset_id VARCHAR(20) PRIMARY KEY REFERENCES Asset(id) ON DELETE CASCADE ON UPDATE CASCADE,
    major_asset_id VARCHAR(20) REFERENCES Asset(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Comment (
    id SERIAL PRIMARY KEY,
    asset_id VARCHAR(20) REFERENCES Asset(id) ON DELETE CASCADE ON UPDATE CASCADE,
    text VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
    );
