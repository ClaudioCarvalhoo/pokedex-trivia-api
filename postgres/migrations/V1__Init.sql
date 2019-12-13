CREATE SCHEMA IF NOT EXISTS trivia;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE trivia.CATEGORY (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    short_name TEXT NOT NULL,
    parent_category TEXT REFERENCES trivia.CATEGORY(id)
);

CREATE TABLE trivia.QUESTION (
   id UUID PRIMARY KEY,
   image_url TEXT,
   stem TEXT NOT NULL
  );

CREATE TABLE trivia.ALTERNATIVE (
    id UUID PRIMARY KEY,
    text TEXT NOT NULL,
    image_url TEXT,
    correct BOOLEAN NOT NULL,
    question_id UUID NOT NULL REFERENCES trivia.QUESTION(id)
);

CREATE SEQUENCE trivia.room_seq AS BIGINT;
CREATE TABLE trivia.ROOM (
    id BIGINT DEFAULT nextval('trivia.room_seq') PRIMARY KEY
);

CREATE TABLE trivia.SCORE (
  room_id BIGINT NOT NULL REFERENCES trivia.ROOM(id),
  username TEXT NOT NULL,
  score BIGINT NOT NULL,
  PRIMARY KEY (room_id, username)
);

CREATE TABLE trivia.ROOM_QUESTION (
  room_id BIGINT NOT NULL REFERENCES trivia.ROOM(id),
  question_id UUID NOT NULL REFERENCES trivia.QUESTION(id),
  PRIMARY KEY (room_id, question_id)
);

CREATE TABLE trivia.ROOM_CATEGORY (
  room_id BIGINT NOT NULL REFERENCES trivia.ROOM(id),
  category_id TEXT NOT NULL REFERENCES trivia.CATEGORY(id),
  PRIMARY KEY (room_id, category_id)
);