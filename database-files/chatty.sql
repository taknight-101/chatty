CREATE SCHEMA "chatty";

CREATE TABLE "chatty"."users" (
  "id" bigserial PRIMARY KEY,
  "username" varchar NOT NULL,
  "password" varchar,
  "created_at" timestamp DEFAULT (now())
);

CREATE TABLE "chatty"."message_types" (
  "id" bigserial PRIMARY KEY,
  "type_name" varchar NOT NULL,
  "code" int NOT NULL
);

CREATE TABLE "chatty"."messages" (
  "id" bigserial,
  "type" bigint,
  "from_user" bigint,
  "from_user_username" varchar,
  "to_user" bigint,
  "content" varchar,
  "room_id" bigint,
  "created_at" timestamp
);

CREATE TABLE "chatty"."chat_rooms" (
  "id" bigserial PRIMARY KEY,
  "name" varchar,
  "description" varchar,
  "created_at" timestamp
);

CREATE TABLE "chatty"."chat_rooms_members" (
  "id" bigserial PRIMARY KEY,
  "room_id" bigint,
  "member_id" bigint
);

CREATE INDEX ON "chatty"."messages" ("from_user");

CREATE INDEX ON "chatty"."messages" ("to_user");

CREATE INDEX ON "chatty"."messages" ("room_id");

CREATE INDEX ON "chatty"."messages" ("from_user", "to_user");

CREATE INDEX ON "chatty"."messages" ("from_user", "to_user", "room_id");

CREATE INDEX ON "chatty"."chat_rooms_members" ("room_id", "member_id");

ALTER TABLE "chatty"."messages" ADD FOREIGN KEY ("type") REFERENCES "chatty"."message_types" ("id");

ALTER TABLE "chatty"."messages" ADD FOREIGN KEY ("from_user") REFERENCES "chatty"."users" ("id");

ALTER TABLE "chatty"."messages" ADD FOREIGN KEY ("to_user") REFERENCES "chatty"."users" ("id");

ALTER TABLE "chatty"."messages" ADD FOREIGN KEY ("room_id") REFERENCES "chatty"."chat_rooms" ("id");

ALTER TABLE "chatty"."chat_rooms_members" ADD FOREIGN KEY ("room_id") REFERENCES "chatty"."chat_rooms" ("id");

ALTER TABLE "chatty"."chat_rooms_members" ADD FOREIGN KEY ("member_id") REFERENCES "chatty"."users" ("id");
