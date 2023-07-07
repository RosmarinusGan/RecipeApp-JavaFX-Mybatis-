/*
 Navicat Premium Data Transfer

 Source Server         : recipe_db
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : recipe_db

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 07/07/2023 04:13:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ingredient_table
-- ----------------------------
DROP TABLE IF EXISTS `ingredient_table`;
CREATE TABLE `ingredient_table`  (
  `ingredientId` bigint NOT NULL,
  `recipeId` bigint NOT NULL,
  `ingredientName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `quantity` float NOT NULL,
  `unit` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `description` mediumtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL,
  PRIMARY KEY (`ingredientId`, `recipeId`) USING BTREE,
  UNIQUE INDEX `IngredientID_UNIQUE`(`ingredientId` ASC) USING BTREE,
  INDEX `ReferedRecipe_idx`(`recipeId` ASC) USING BTREE,
  CONSTRAINT `ReferedRecipe` FOREIGN KEY (`recipeId`) REFERENCES `recipe_table` (`recipeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ingredient_table
-- ----------------------------
INSERT INTO `ingredient_table` VALUES (3842, 4310, 'oyster sauce', 2, 'tablespoons', '');
INSERT INTO `ingredient_table` VALUES (8260, 862, 'Pork belly', 250, 'g', 'cut it into thin slices');
INSERT INTO `ingredient_table` VALUES (8848, 3584, 'Garlic', 20, 'g', 'Cut into small pieces');
INSERT INTO `ingredient_table` VALUES (10524, 7628, 'soy sauce', 5, 'g', '');
INSERT INTO `ingredient_table` VALUES (16123, 4310, 'Coca-Cola', 300, 'ml', '');
INSERT INTO `ingredient_table` VALUES (22940, 862, 'Salt', 2, 'g', '');
INSERT INTO `ingredient_table` VALUES (28195, 7628, 'water', 1, 'L', '');
INSERT INTO `ingredient_table` VALUES (32083, 5556, 'Green onion', 100, 'g', 'Cut the green onion into sections');
INSERT INTO `ingredient_table` VALUES (32109, 3584, 'Beef', 1, 'kg', 'Cut beef brisket into large pieces and blanch in cold water.');
INSERT INTO `ingredient_table` VALUES (38770, 7207, 'Eggs', 120, 'g', 'break up eggs, stir and make it mixed evenly');
INSERT INTO `ingredient_table` VALUES (42555, 4310, 'dark soy sauce', 2, 'tablespoons', '');
INSERT INTO `ingredient_table` VALUES (43558, 7628, 'sugar', 20, 'g', '');
INSERT INTO `ingredient_table` VALUES (47683, 7628, 'meat', 500, 'g', '');
INSERT INTO `ingredient_table` VALUES (47933, 4310, 'chicken wings', 500, 'g', '');
INSERT INTO `ingredient_table` VALUES (55945, 5556, 'Chicken breast', 500, 'g', '');
INSERT INTO `ingredient_table` VALUES (58526, 862, 'Hot pepper', 200, 'g', '');
INSERT INTO `ingredient_table` VALUES (60183, 4310, 'ketchup', 2, 'tablespoons', '');
INSERT INTO `ingredient_table` VALUES (60329, 4310, 'vegetable oil', 1, 'tablespoons', '');
INSERT INTO `ingredient_table` VALUES (60427, 5556, 'Pepper', 50, 'g', '');
INSERT INTO `ingredient_table` VALUES (60683, 7207, 'tomatoes', 800, 'g', 'remove the stalk from the tomatoes, cut them into pieces');
INSERT INTO `ingredient_table` VALUES (69751, 6669, 'water', 150, 'ml', '');
INSERT INTO `ingredient_table` VALUES (76138, 3584, 'Tomato', 800, 'g', 'remove the stalk from the tomatoes, cut them into pieces');
INSERT INTO `ingredient_table` VALUES (76465, 4310, 'soy sauce', 2, 'tablespoons', '');
INSERT INTO `ingredient_table` VALUES (83262, 6669, 'glutinous rice flour', 200, 'g', '');
INSERT INTO `ingredient_table` VALUES (85830, 862, 'Fermented black beans', 10, 'g', '');
INSERT INTO `ingredient_table` VALUES (87604, 862, 'Soy sauce', 5, 'g', '');
INSERT INTO `ingredient_table` VALUES (92428, 3584, 'Onion', 150, 'g', 'Cut into small pieces');

-- ----------------------------
-- Table structure for recipe_table
-- ----------------------------
DROP TABLE IF EXISTS `recipe_table`;
CREATE TABLE `recipe_table`  (
  `recipeId` bigint NOT NULL,
  `recipeName` mediumtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `recipeCategory` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  `prepareTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cookTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `recommendServeNumber` int NOT NULL,
  `ownerId` int NOT NULL,
  `instruction` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `imagePath` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`recipeId`) USING BTREE,
  UNIQUE INDEX `RecipeID_UNIQUE`(`recipeId` ASC) USING BTREE,
  INDEX `OwnerID_idx`(`ownerId` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recipe_table
-- ----------------------------
INSERT INTO `recipe_table` VALUES (862, 'Stir-fried meat with chili', 'Dinner', '30 minutes', '30 minutes', 2, 94056489, 'Wash the peeled pork belly and cut it into thin slices. Marinate with soy sauce. Wash and cut fresh peppers into small slices. Put a little cooking oil in the pot first, after the oil is heated, put the soy sauce marinated pork belly, fry and evenly put in the pan. Pick up the pan again, dry fry the chilies, until the surface is scorched, then put in the cooking oil, stir-fry with high heat, and then pour in the just-fried pork belly, add appropriate seasoning according to personal taste, mix in a little sesame oil, stir well.', 'maxresdefault.jpg');
INSERT INTO `recipe_table` VALUES (3584, 'Stewed Beef Brisket with Tomato', 'Dinner', '40 minutes', '1 h 30 minutes', 4, 94056489, 'Heat up a wok with oil, add garlic and onion until fragrant. Pour in the brisket cubes and stir. Add salt, soy sauce and dark soy sauce. Pour in boiling water. Let it cook for a while in an electric pressure cooker. Put the oil and garlic in the pan until fragrant. Stir in the tomato cubes, gently tapping them with a spatula to release the juices. Pour the braised beef brisket cubes into the tomatoes, braise for a while, and then remove from the pot.', 'Stewed Beef.png');
INSERT INTO `recipe_table` VALUES (4310, 'Coca-Cola chicken wings', 'Dinner', '30 minutes', '1 h 30 minutes', 3, 59249984, '    In a bowl, combine the Coca-Cola, soy sauce, dark soy sauce, oyster sauce, ketchup, brown sugar, minced garlic, and grated ginger. Mix well to make the marinade.\n    Place the chicken wings in a ziplock bag or a bowl, and pour the marinade over them. Make sure the chicken wings are evenly coated. Marinate in the refrigerator for at least 2 hours or overnight for best flavor.\n    Heat the vegetable oil in a large skillet or frying pan over medium heat. Remove the chicken wings from the marinade, reserving the marinade for later use.\n    Place the chicken wings in the hot skillet and cook until they are browned on both sides, about 5 minutes per side.\n    Pour the reserved marinade into the skillet with the chicken wings. Bring it to a boil, then reduce the heat to low and simmer for about 15-20 minutes, or until the chicken wings are cooked through and the sauce has thickened.\n    Stir the chicken wings occasionally to make sure they are evenly coated with the sauce.\n    Once the chicken wings are cooked, remove them from the skillet and place them on a serving plate. Garnish with chopped green onions (scallions) and sesame seeds if desired.\n    Serve the Coca-Cola chicken wings hot as an appetizer or as a main course with steamed rice or noodles.', 'colachicken.jpg');
INSERT INTO `recipe_table` VALUES (5556, 'Kung Pao Chicken', 'Lunch', '30 minutes', '25 minutes', 3, 94056489, 'Pat the chicken breast with the back of a knife and cut into thumbnail sized cubes. Use 1 tablespoon of cooking wine, 1/2 tablespoon of cooking oil, 1/2 teaspoon of white pepper, 1/2 teaspoon of salt, 1 teaspoon of starch, and pickle for 10 minutes to taste.\nPut oil in the wok and stir fry the chicken until it turns white. Add dried chillies, scallions and a teaspoon of ground pepper and fry until fragrant. Add the sauce and saute over high heat until thick and dry.', 'Chicken.png');
INSERT INTO `recipe_table` VALUES (6669, 'Glutinous rice balls', 'Afternoon Tea', '50 minutes', '10 minutes', 2, 59249984, '    In a mixing bowl, combine the glutinous rice flour with water gradually. Start with 150 milliliters of water and add more if needed. Mix the water into the flour until it forms a smooth and slightly sticky dough.\n    If desired, divide the dough into portions and add food coloring or natural coloring agents to each portion. Knead the dough until the color is evenly distributed.\n    Take a small portion of the dough (about 1 tablespoon) and flatten it in your palm.\n    Place a teaspoon of your chosen filling in the center of the flattened dough.\n    Carefully seal the dough around the filling, rolling it into a smooth ball shape. Ensure there are no cracks or openings in the dough.\n    Repeat the process until all the dough and filling are used.\n    Bring a pot of water to a boil. Add the glutinous rice balls to the boiling water and gently stir to prevent sticking.\n    Cook the rice balls for about 5-7 minutes or until they float to the surface.\n    Once the rice balls are floating, continue boiling them for an additional 1-2 minutes to ensure they are thoroughly cooked.\n    Using a slotted spoon, transfer the cooked rice balls to a bowl of cold water to cool and prevent sticking.\n    Serve the glutinous rice balls in a bowl with some of the cooking water or in a sweet syrup, if desired.', 'riceballs.jpg');
INSERT INTO `recipe_table` VALUES (7207, 'scrambled egg with tomato', 'Lunch', '40 minutes', '15 minutes', 2, 94056489, 'put some oil into the pan, pour the egg mixture after oil heating-up; Later，stir-fry and cut into small pieces by truner; After completion, bring it out and wait for back use. Now we need deal tomatoes .Pour in a little oil and put tomatoes into the pan, cook it. When we see tomatoes getting soft , change it into a small fire , add proper salts and sugars, continue cooking. Mix eggs with tomatoes uniformity, turn off the fire. The delicious dish was finished.', 'egg.png');
INSERT INTO `recipe_table` VALUES (7628, 'hong shao rou', 'Lunch', '30 minutes', '1 h 30 minutes', 2, 94056489, '    In a pot of boiling water, blanch the pork belly for a few minutes to remove any impurities. Drain and set aside.\n    Heat a tablespoon of oil in a deep pan or wok over medium heat. Add the ginger, garlic, star anise, and cinnamon stick. Stir-fry until fragrant.\n    Add the pork belly to the pan and stir-fry until it starts to brown.\n    Pour in the soy sauce, dark soy sauce, sugar, and Shaoxing wine. Mix well to coat the pork belly evenly.\n    Add water to the pan until the pork belly is almost covered. Bring to a boil, then reduce the heat to low and cover the pan.\n    Simmer for about 1.5 to 2 hours, or until the pork belly becomes tender and the sauce thickens. Stir occasionally and add more water if needed.\n    Once the pork belly is cooked, remove the lid and increase the heat to medium-high. Stir-fry the pork belly until the sauce thickens and coats the meat.\n    Serve the red-braised pork belly hot with steamed rice or as desired.', '_hsr.jpg');

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table`  (
  `userId` int NOT NULL,
  `userName` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`userId`) USING BTREE,
  UNIQUE INDEX `UserID_UNIQUE`(`userId` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_table
-- ----------------------------
INSERT INTO `user_table` VALUES (59249984, 'rosm', '12345');
INSERT INTO `user_table` VALUES (94056489, 'teriri', '123456');

SET FOREIGN_KEY_CHECKS = 1;
