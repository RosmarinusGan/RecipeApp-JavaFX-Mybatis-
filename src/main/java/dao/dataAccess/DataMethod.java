package dao.dataAccess;

import dao.mappers.IngredientPOJO;
import dao.mappers.RecipePOJO;
import dao.mappers.UserPOJO;

import java.util.List;

public interface DataMethod {
    public boolean insertUser(UserPOJO user);
    public boolean updateUser(UserPOJO user);
    public boolean deleteUser(UserPOJO user);
    public List<UserPOJO> getUser(int userId);
    public List<UserPOJO> getUser(String userName);

    public boolean insertRecipe(RecipePOJO recipe);
    public boolean insertIngredient(IngredientPOJO ingredient);
    public boolean updateRecipe(RecipePOJO recipe);
    public boolean updateIngredient(IngredientPOJO ingredient);
    public boolean deleteRecipe(RecipePOJO recipe);
    public List<RecipePOJO> getRecipe_Owner(int ownerId);
    public List<RecipePOJO> getRecipe_Name(String recipeName);
    public List<RecipePOJO> getRecipe_Category(String recipeCategory);
    public List<RecipePOJO> getRecipe_Id(int recipeId);
    public List<RecipePOJO> getRecipe_CateAndName(String recipeCategory, String recipeName);
    public List<RecipePOJO> getRecipe_All();
}
