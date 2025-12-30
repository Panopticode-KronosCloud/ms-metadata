-- case insensitive text type
--CREATE EXTENSION IF NOT EXISTS citext;

CREATE SCHEMA cookbook;

CREATE TABLE cookbook.recipe (
  id             UUID         DEFAULT gen_random_uuid() PRIMARY KEY,
  name           VARCHAR(50)  NOT NULL,
  servings       INTEGER      NOT NULL,
  text_intro     TEXT,
  picture        VARCHAR(50),
  thumbnail      VARCHAR(50),
  difficulty     VARCHAR(50)  NOT NULL,
  prep_time_m    INTEGER      NOT NULL,
  cook_time_m    INTEGER,
  text_outro     TEXT
);

CREATE TABLE cookbook.ingredient (
  id             UUID         DEFAULT gen_random_uuid() PRIMARY KEY,
  name           VARCHAR(50)  NOT NULL
);
-- add index, with the advantage that name will be case-insensitively unique
CREATE UNIQUE INDEX ingredient_unique_idx on cookbook.ingredient (LOWER(name));

CREATE TABLE cookbook.recipe_ingredient (
  recipe_id      UUID         NOT NULL,
  ingredient_id  UUID         NOT NULL,
  quantity       REAL,
  unit           VARCHAR(10),
  index          INTEGER      NOT NULL,

  PRIMARY KEY (recipe_id, ingredient_id),
  CONSTRAINT fk_ri_recipe FOREIGN KEY(recipe_id) REFERENCES cookbook.recipe(id)
  ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_ri_ingredient FOREIGN KEY(ingredient_id) REFERENCES cookbook.ingredient(id),
  CONSTRAINT ingredient_unique_key UNIQUE (recipe_id, index)
);