CREATE TABLE "category" (
  "id" integer PRIMARY KEY,
  "name" varchar,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "products" (
  "id" integer PRIMARY KEY,
  "category_id" integer,
  "name" varchar NOT NULL,
  "description" varchar,
  "price" double NOT NULL,
  "main_image" varchar,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "product_variant" (
  "id" integer PRIMARY KEY,
  "product_id" integer,
  "price" double,
  "stock" int,
  "image_id" integer,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "product_option" (
  "id" integer PRIMARY KEY,
  "product_id" integer,
  "name" varchar NOT NULL,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "product_option_choice" (
  "id" integer PRIMARY KEY,
  "product_option_id" integer,
  "value" varchar NOT NULL,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "product_variant_choice" (
  "id" integer PRIMARY KEY,
  "product_variant_id" integer,
  "product_option_choice_id" integer,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "product_image" (
  "id" integer PRIMARY KEY,
  "product_variant_id" integer,
  "url" varchar,
  "created_at" timestamp,
  "updated_at" timestamp
);

ALTER TABLE "category" ADD FOREIGN KEY ("name") REFERENCES "products" ("category_id");

ALTER TABLE "products" ADD FOREIGN KEY ("id") REFERENCES "product_option" ("product_id");

ALTER TABLE "product_option" ADD FOREIGN KEY ("id") REFERENCES "product_option_choice" ("product_option_id");

ALTER TABLE "products" ADD FOREIGN KEY ("id") REFERENCES "product_variant" ("product_id");

ALTER TABLE "product_variant" ADD FOREIGN KEY ("id") REFERENCES "product_variant_choice" ("product_variant_id");

ALTER TABLE "product_option_choice" ADD FOREIGN KEY ("id") REFERENCES "product_variant_choice" ("product_option_choice_id");

ALTER TABLE "product_variant" ADD FOREIGN KEY ("id") REFERENCES "product_image" ("product_variant_id");
