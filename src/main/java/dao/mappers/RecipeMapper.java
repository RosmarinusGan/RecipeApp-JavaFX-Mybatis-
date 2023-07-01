package dao.mappers;

import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface RecipeMapper {
    public boolean addRecipe(@Param("recipe") RecipePOJO recipe);
    public boolean deleteRecipe(@Param("recipe") RecipePOJO recipe);
    public boolean updateRecipe(@Param("recipe") RecipePOJO recipe);
    public List<RecipePOJO> getRecipeByOwner(@Param("owner") int OwnerId);
    public List<RecipePOJO> getRecipeByName(@Param("name") String recipeName);
    public List<RecipePOJO> getRecipeByCategory(@Param("category") String recipeCategory);
    public List<RecipePOJO> getRecipeById(@Param("id") int recipeId);
    public List<RecipePOJO> getRecipeByCategoryAndName(@Param("category") String category, @Param("name") String recipeName);
    public List<RecipePOJO> getRecipeAll();
}
