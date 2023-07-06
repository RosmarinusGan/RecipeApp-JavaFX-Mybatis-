package dao.dataAccess;

import dao.mappers.IngredientPOJO;
import dao.mappers.RecipePOJO;
import dao.mappers.UserPOJO;

import java.util.List;

/**
 * The interface Data method displays the methods of Data Access.
 *
 * @author Liming Gan
 */
public interface DataMethod {
    /**
     * Insert an user.
     *
     * @param user the user
     * @return the boolean, inserting successfully return true else return false.
     */
    public boolean insertUser(UserPOJO user);

    /**
     * Update an user.
     *
     * @param user the user
     * @return the boolean, updating successfully return true else return false.
     */
    public boolean updateUser(UserPOJO user);

    /**
     * Delete user.
     *
     * @param user the user
     * @return the boolean, deleting successfully return true else return false.
     */
    public boolean deleteUser(UserPOJO user);

    /**
     * Gets user.
     *
     * @param userId the user id
     * @return the user, a list of user with corresponding id.
     */
    public List<UserPOJO> getUser(int userId);

    /**
     * Gets user.
     *
     * @param userName the user name
     * @return the user, a list of user with corresponding name.
     */
    public List<UserPOJO> getUser(String userName);

    /**
     * Insert recipe.
     *
     * @param recipe the recipe
     * @return the boolean, inserting successfully return true else return false.
     */
    public boolean insertRecipe(RecipePOJO recipe);

    /**
     * Insert ingredient.
     *
     * @param ingredient the ingredient
     * @return the boolean, inserting successfully return true else return false.
     */
    public boolean insertIngredient(IngredientPOJO ingredient);

    /**
     * Update recipe.
     *
     * @param recipe the recipe
     * @return the boolean, updating successfully return true else return false.
     */
    public boolean updateRecipe(RecipePOJO recipe);

    /**
     * Update ingredient.
     *
     * @param ingredient the ingredient
     * @return the boolean, updating successfully return true else return false.
     */
    public boolean updateIngredient(IngredientPOJO ingredient);

    /**
     * Delete recipe.
     *
     * @param recipe the recipe
     * @return the boolean, deleting successfully return true else return false.
     */
    public boolean deleteRecipe(RecipePOJO recipe);

    /**
     * Gets recipe owner.
     *
     * @param ownerId the owner id
     * @return the recipe owner, a list of recipe with corresponding owner id.
     */
    public List<RecipePOJO> getRecipe_Owner(int ownerId);

    /**
     * Gets recipe name.
     *
     * @param recipeName the recipe name
     * @return the recipe name, a list of recipe with corresponding recipe name.
     */
    public List<RecipePOJO> getRecipe_Name(String recipeName);

    /**
     * Gets recipe category.
     *
     * @param recipeCategory the recipe category
     * @return the recipe category, a list of recipe with corresponding recipe category.
     */
    public List<RecipePOJO> getRecipe_Category(String recipeCategory);

    /**
     * Gets recipe id.
     *
     * @param recipeId the recipe id
     * @return the recipe id, a list of recipe with corresponding recipe id.
     */
    public List<RecipePOJO> getRecipe_Id(int recipeId);

    /**
     * Gets recipe cate and name.
     *
     * @param recipeCategory the recipe category
     * @param recipeName     the recipe name
     * @return the recipe cate and name, list of recipe with corresponding recipe name and recipe category.
     */
    public List<RecipePOJO> getRecipe_CateAndName(String recipeCategory, String recipeName);

    /**
     * Gets recipe all.
     *
     * @return the recipe all, list of all recipes.
     */
    public List<RecipePOJO> getRecipe_All();
}
