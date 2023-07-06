package dao.mappers;

import org.apache.ibatis.type.Alias;
import java.io.Serializable;
import java.util.List;

/**
 * The type Recipe pojo.
 *
 * @author Liming Gan
 */
@Alias("RecipePOJO")
public class RecipePOJO implements Serializable {
    private int recipeId;
    private String recipeName;
    private String recipeCategory;
    private String prepareTime;
    private String cookTime;
    private int recommendServeNumber;
    private int ownerId;
    private String instruction;
    private String imagePath;
    private List<IngredientPOJO> ingredients;

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
     * Gets recipe name.
     *
     * @return the recipe name
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * Sets recipe name.
     *
     * @param recipeName the recipe name
     */
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    /**
     * Gets recipe category.
     *
     * @return the recipe category
     */
    public String getRecipeCategory() {
        return recipeCategory;
    }

    /**
     * Sets recipe category.
     *
     * @param recipeCategory the recipe category
     */
    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    /**
     * Gets prepare time.
     *
     * @return the prepare time
     */
    public String getPrepareTime() {
        return prepareTime;
    }

    /**
     * Sets prepare time.
     *
     * @param prepareTime the prepare time
     */
    public void setPrepareTime(String prepareTime) {
        this.prepareTime = prepareTime;
    }

    /**
     * Gets cook time.
     *
     * @return the cook time
     */
    public String getCookTime() {
        return cookTime;
    }

    /**
     * Sets cook time.
     *
     * @param cookTime the cook time
     */
    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    /**
     * Gets recommend serve number.
     *
     * @return the recommend serve number
     */
    public int getRecommendServeNumber() {
        return recommendServeNumber;
    }

    /**
     * Sets recommend serve number.
     *
     * @param recommendServeNumber the recommend serve number
     */
    public void setRecommendServeNumber(int recommendServeNumber) {
        this.recommendServeNumber = recommendServeNumber;
    }

    /**
     * Gets owner id.
     *
     * @return the owner id
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     * Sets owner id.
     *
     * @param ownerId the owner id
     */
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Gets instruction.
     *
     * @return the instruction
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * Sets instruction.
     *
     * @param instruction the instruction
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Gets image path.
     *
     * @return the image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets image path.
     *
     * @param imagePath the image path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Gets ingredients.
     *
     * @return the ingredients
     */
    public List<IngredientPOJO> getIngredients() {
        return ingredients;
    }

    /**
     * Sets ingredients.
     *
     * @param ingredients the ingredients
     */
    public void setIngredients(List<IngredientPOJO> ingredients) {
        this.ingredients = ingredients;
    }
}
