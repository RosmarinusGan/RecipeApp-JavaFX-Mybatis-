package dao.dataAccess;

import dao.mappers.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DataAccess implements DataMethod{
    //Singleton
    private DataAccess(){
        init();
    }
    private static class SingletonInstance{
        private static final DataAccess singleton = new DataAccess();
    }
    public static DataAccess getInstance(){
        return SingletonInstance.singleton;
    }

    @Override
    public boolean insertUser(UserPOJO user) {
        return userMapper.addUser(user);
    }

    @Override
    public boolean updateUser(UserPOJO user) {
        return userMapper.updateUser(user);
    }

    @Override
    public boolean deleteUser(UserPOJO user) {
        return userMapper.deleteUser(user);
    }

    @Override
    public List<UserPOJO> getUser(int userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public List<UserPOJO> getUser(String userName) {
        return userMapper.getUserByName(userName);
    }

    @Override
    public boolean insertRecipe(RecipePOJO recipe) {
        return recipeMapper.addRecipe(recipe);
    }

    @Override
    public boolean insertIngredient(IngredientPOJO ingredient) {
        return ingredientMapper.addIngredient(ingredient);
    }

    @Override
    public boolean updateRecipe(RecipePOJO recipe) {
        return recipeMapper.updateRecipe(recipe);
    }

    @Override
    public boolean updateIngredient(IngredientPOJO ingredient) {
        return ingredientMapper.updateIngredient(ingredient);
    }

    @Override
    public boolean deleteRecipe(RecipePOJO recipe) {
        return recipeMapper.deleteRecipe(recipe);
    }

    @Override
    public List<RecipePOJO> getRecipe_Owner(int ownerId) {
        return recipeMapper.getRecipeByOwner(ownerId);
    }

    @Override
    public List<RecipePOJO> getRecipe_Name(String recipeName) {
        return recipeMapper.getRecipeByName(recipeName);
    }

    @Override
    public List<RecipePOJO> getRecipe_Category(String recipeCategory) {
        return recipeMapper.getRecipeByCategory(recipeCategory);
    }

    @Override
    public List<RecipePOJO> getRecipe_Id(int recipeId) {
        return recipeMapper.getRecipeById(recipeId);
    }

    @Override
    public List<RecipePOJO> getRecipe_CateAndName(String recipeCategory, String recipeName) {
        return recipeMapper.getRecipeByCategoryAndName(recipeCategory, recipeName);
    }

    @Override
    public List<RecipePOJO> getRecipe_All() {
        return recipeMapper.getRecipeAll();
    }

    private void init(){
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            //SqlSession sqlSession = sqlSessionFactory.openSession(false);

            recipeMapper = sqlSession.getMapper(RecipeMapper.class);
            userMapper = sqlSession.getMapper(UserMapper.class);
            ingredientMapper = sqlSession.getMapper(IngredientMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private RecipeMapper recipeMapper;
    private UserMapper userMapper;
    private IngredientMapper ingredientMapper;
}
