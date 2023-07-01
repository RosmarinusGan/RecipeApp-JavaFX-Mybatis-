package dao.mappers;

import org.apache.ibatis.type.Alias;
import java.io.Serializable;
import java.util.List;

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

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public String getPrepareTime() {
        return prepareTime;
    }

    public void setPrepareTime(String prepareTime) {
        this.prepareTime = prepareTime;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public int getRecommendServeNumber() {
        return recommendServeNumber;
    }

    public void setRecommendServeNumber(int recommendServeNumber) {
        this.recommendServeNumber = recommendServeNumber;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<IngredientPOJO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientPOJO> ingredients) {
        this.ingredients = ingredients;
    }
}
