package dao.mappers;

import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * The interface Recipe mapper.
 * Manage the mapper between POJO and xml.
 *
 * @author Liming Gan
 */
public interface RecipeMapper {
    /**
     * Add recipe boolean.
     *
     * @param recipe the recipe
     * @return the boolean
     */
    public boolean addRecipe(@Param("recipe") RecipePOJO recipe);

    /**
     * Delete recipe boolean.
     *
     * @param recipe the recipe
     * @return the boolean
     */
    public boolean deleteRecipe(@Param("recipe") RecipePOJO recipe);

    /**
     * Update recipe boolean.
     *
     * @param recipe the recipe
     * @return the boolean
     */
    public boolean updateRecipe(@Param("recipe") RecipePOJO recipe);

    /**
     * Gets recipe by owner.
     *
     * @param OwnerId the owner id
     * @return the recipe by owner
     */
    public List<RecipePOJO> getRecipeByOwner(@Param("owner") int OwnerId);

    /**
     * Gets recipe by name.
     *
     * @param recipeName the recipe name
     * @return the recipe by name
     */
    public List<RecipePOJO> getRecipeByName(@Param("name") String recipeName);

    /**
     * Gets recipe by category.
     *
     * @param recipeCategory the recipe category
     * @return the recipe by category
     */
    public List<RecipePOJO> getRecipeByCategory(@Param("category") String recipeCategory);

    /**
     * Gets recipe by id.
     *
     * @param recipeId the recipe id
     * @return the recipe by id
     */
    public List<RecipePOJO> getRecipeById(@Param("id") int recipeId);

    /**
     * Gets recipe by category and name.
     *
     * @param category   the category
     * @param recipeName the recipe name
     * @return the recipe by category and name
     */
    public List<RecipePOJO> getRecipeByCategoryAndName(@Param("category") String category, @Param("name") String recipeName);

    /**
     * Gets recipe all.
     *
     * @return the recipe all
     */
    public List<RecipePOJO> getRecipeAll();
}
