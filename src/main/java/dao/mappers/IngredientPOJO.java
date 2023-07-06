package dao.mappers;

import org.apache.ibatis.type.Alias;
import java.io.Serializable;

/**
 * The type Ingredient pojo.
 *
 * @author Liming Gan
 */
@Alias("IngredientPOJO")
public class IngredientPOJO implements Serializable {
    private int ingredientId;
    private int recipeId;
    private String ingredientName;
    private float quantity;
    private String unit;
    private String description;

    /**
     * Gets ingredient id.
     *
     * @return the ingredient id
     */
    public int getIngredientId() {
        return ingredientId;
    }

    /**
     * Sets ingredient id.
     *
     * @param ingredientId the ingredient id
     */
    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    /**
     * Gets recipe id.
     *
     * @return the recipe id
     */
    public int getRecipeId() {
        return recipeId;
    }

    /**
     * Sets recipe id.
     *
     * @param recipeId the recipe id
     */
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    /**
     * Gets ingredient name.
     *
     * @return the ingredient name
     */
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * Sets ingredient name.
     *
     * @param ingredientName the ingredient name
     */
    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public float getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets unit.
     *
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets unit.
     *
     * @param unit the unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
