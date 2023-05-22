-- https://dbdiagram.io/d/644b9030dca9fb07c432970a

CREATE TABLE `users` (
  `id` integer UNIQUE PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255) UNIQUE,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `auth_level` integer NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp
);

CREATE TABLE `subscriptions` (
  `id` integer UNIQUE PRIMARY KEY AUTO_INCREMENT,
  `user_id` integer NOT NULL,
  `theme_id` integer NOT NULL
);

CREATE TABLE `themes` (
  `id` integer UNIQUE PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp
);

CREATE TABLE `articles` (
  `id` integer UNIQUE PRIMARY KEY AUTO_INCREMENT,
  `user_id` integer NOT NULL,
  `theme_id` integer NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp
);

CREATE TABLE `comments` (
  `id` integer UNIQUE PRIMARY KEY AUTO_INCREMENT,
  `article_id` integer NOT NULL,
  `user_id` integer NOT NULL,
  `content` text NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp
);

ALTER TABLE `subscriptions` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

ALTER TABLE `subscriptions` ADD FOREIGN KEY (`theme_id`) REFERENCES `themes` (`id`) ON DELETE CASCADE;

ALTER TABLE `articles` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

ALTER TABLE `articles` ADD FOREIGN KEY (`theme_id`) REFERENCES `themes` (`id`);

ALTER TABLE `comments` ADD FOREIGN KEY (`article_id`) REFERENCES `articles` (`id`) ON DELETE CASCADE;

ALTER TABLE `comments` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
