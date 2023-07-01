package dao.dataAccess;

import dao.mappers.IngredientPOJO;
import dao.mappers.RecipePOJO;
import dao.mappers.UserPOJO;
import org.junit.Before;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class DataAccessTest {
    DataAccess da = DataAccess.getInstance();


    @org.junit.jupiter.api.Test
    void getUser() {
        UserPOJO user1 = new UserPOJO();
        user1.setUserId(001);
        user1.setUserName("liming");
        user1.setPassword("123456");
        UserPOJO user2 = new UserPOJO();
        user2.setUserId(002);
        user2.setUserName("liming");
        user2.setPassword("789");

        da.insertUser(user1);
        da.insertUser(user2);
        List<UserPOJO> list = da.getUser("liming");
        assertEquals(001, list.get(0).getUserId());
        assertEquals(002, list.get(1).getUserId());
    }


    @org.junit.jupiter.api.Test
    void deleteRecipe() {
        RecipePOJO recipe = new RecipePOJO();
        recipe.setRecipeId(0001);
        recipe.setRecipeName("hong shao rou");
        recipe.setRecipeCategory("lunch");
        recipe.setPrepareTime("5 min");
        recipe.setCookTime("10 min");
        recipe.setRecommendServeNumber(2);
        recipe.setOwnerId(001);
        recipe.setInstruction("none");

        IngredientPOJO ing = new IngredientPOJO();
        ing.setRecipeId(0001);
        ing.setIngredientName("der Zuck");
        ing.setQuantity(100);
        ing.setUnit("g");
        ing.setIngredientId(1);
        ing.setDescription("none1");

        IngredientPOJO ing2 = new IngredientPOJO();
        ing2.setRecipeId(0001);
        ing2.setIngredientName("der Essig");
        ing2.setQuantity(1);
        ing2.setUnit("l");
        ing2.setIngredientId(2);
        ing2.setDescription("none2");

        da.insertRecipe(recipe);
        da.insertIngredient(ing);
        da.insertIngredient(ing2);

        List<RecipePOJO> list = da.getRecipe_Name("hong shao rou");
        assertEquals(2, list.get(0).getIngredients().size());

        da.deleteRecipe(recipe);
        da.insertRecipe(recipe);
        da.insertIngredient(ing);
        da.insertIngredient(ing2);
        assertEquals(2, list.get(0).getIngredients().size());
    }

    @org.junit.jupiter.api.Test
    void getRecipe_Owner() {

    }

    @org.junit.jupiter.api.Test
    void getRecipe_Name() {
    }

    @org.junit.jupiter.api.Test
    void getRecipe_Category() {
    }

    @org.junit.jupiter.api.Test
    void getRecipe_All() {
        RecipePOJO recipe = new RecipePOJO();
        recipe.setRecipeId(0001);
        recipe.setRecipeName("hong shao rou");
        recipe.setRecipeCategory("lunch");
        recipe.setPrepareTime("5 min");
        recipe.setCookTime("10 min");
        recipe.setRecommendServeNumber(2);
        recipe.setOwnerId(001);
        recipe.setInstruction("none");

        RecipePOJO recipe2 = new RecipePOJO();
        recipe2.setRecipeId(0002);
        recipe2.setRecipeName("cola chicken");
        recipe2.setRecipeCategory("lunch");
        recipe2.setPrepareTime("8 min");
        recipe2.setCookTime("16 min");
        recipe2.setRecommendServeNumber(2);
        recipe2.setOwnerId(001);
        recipe2.setInstruction("none");

        IngredientPOJO ing = new IngredientPOJO();
        ing.setRecipeId(0001);
        ing.setIngredientName("der Zuck");
        ing.setQuantity(100);
        ing.setUnit("g");
        ing.setIngredientId(1);
        ing.setDescription("none1");

        IngredientPOJO ing2 = new IngredientPOJO();
        ing2.setRecipeId(0001);
        ing2.setIngredientName("der Essig");
        ing2.setQuantity(1);
        ing2.setUnit("l");
        ing2.setIngredientId(2);
        ing2.setDescription("none2");

        IngredientPOJO ing3 = new IngredientPOJO();
        ing3.setRecipeId(0001);
        ing3.setIngredientName("das fleish");
        ing3.setQuantity(3);
        ing3.setUnit("kg");
        ing3.setIngredientId(3);
        ing3.setDescription("none3");

        assertEquals(true, da.insertRecipe(recipe));
        da.insertRecipe(recipe2);
        da.insertIngredient(ing);
        da.insertIngredient(ing2);
        da.insertIngredient(ing3);

        List<RecipePOJO> list = da.getRecipe_Name("hong shao rou");
        assertEquals(0001, list.get(0).getRecipeId());
        //assertEquals(0002, list.get(1).getRecipeId());
        assertEquals(3 , list.get(0).getIngredients().size());
    }
}