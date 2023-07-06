package dao.mappers;

import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * The interface Ingredient mapper.
 * Manage the mapper between POJO and xml.
 *
 * @author Liming Gan
 */
public interface IngredientMapper {
    /**
     * Add ingredient boolean.
     *
     * @param ingredient the ingredient
     * @return the boolean
     */
    public boolean addIngredient(@Param("ingredient") IngredientPOJO ingredient);

    /**
     * Delete ingredient boolean.
     *
     * @param ingredient the ingredient
     * @return the boolean
     */
    public boolean deleteIngredient(@Param("ingredient") IngredientPOJO ingredient);

    /**
     * Update ingredient boolean.
     *
     * @param ingredient the ingredient
     * @return the boolean
     */
    public boolean updateIngredient(@Param("ingredient") IngredientPOJO ingredient);

    /**
     * Gets ingredient recipe.
     *
     * @param recipeId the recipe id
     * @return the ingredient recipe
     */
    public List<IngredientPOJO> getIngredient_recipe(@Param("recipeId") int recipeId);
}
