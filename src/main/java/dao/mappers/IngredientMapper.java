package dao.mappers;

import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface IngredientMapper {
    public boolean addIngredient(@Param("ingredient") IngredientPOJO ingredient);
    public boolean deleteIngredient(@Param("ingredient") IngredientPOJO ingredient);
    public boolean updateIngredient(@Param("ingredient") IngredientPOJO ingredient);
    public List<IngredientPOJO> getIngredient_recipe(@Param("recipeId") int recipeId);
}
