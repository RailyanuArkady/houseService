create sequence "tag-sequence" start with 1 increment by 1;
create sequence "house-sequence" start with 1 increment by 1;
create sequence "photo-sequence" start with 1 increment by 1;

CREATE TABLE houses
(
    id              BIGINT         NOT NULL,
    external_id     UUID           NOT NULL UNIQUE,
    name            VARCHAR(100)   NOT NULL,
    description     VARCHAR(2000)  NOT NULL,
    persons_count   INT            NOT NULL,
    area            INT            NOT NULL,
    price_per_night NUMERIC(11, 2) NOT NULL,
    address         VARCHAR(500)   NOT NULL UNIQUE,

    latitude        VARCHAR(10)    NOT NULL,
    longitude       VARCHAR(10)    NOT NULL,

    created_at      TIMESTAMP(6)   NOT NULL,
    updated_at      TIMESTAMP(6)   NOT NULL,
    is_deleted      BOOLEAN        NOT NULL,

    PRIMARY KEY (id),
    CHECK (latitude:: NUMERIC >= -90.000000 AND latitude:: NUMERIC <= 90.000000) ,
    CHECK (longitude::NUMERIC >= -180.000000 AND longitude::NUMERIC <= 180.000000)
);

create table photos
(
    id          BIGINT  NOT NULL,
    external_id UUID    NOT NULL,
    is_main     BOOLEAN NOT NULL,
    house_id    BIGINT  NOT NULL REFERENCES houses (id),
    PRIMARY KEY (id)
);

create table tags
(
    id          BIGINT NOT NULL,
    external_id UUID   NOT NULL,
    photo_id    UUID   NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE house_tags
(
    house_id BIGINT NOT NULL,
    tag_id   BIGINT NOT NULL,
    PRIMARY KEY (house_id, tag_id),
    FOREIGN KEY (house_id) REFERENCES houses (id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tags (id) ON DELETE CASCADE
);