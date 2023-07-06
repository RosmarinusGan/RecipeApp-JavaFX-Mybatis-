package dao.dataAccess;

import dao.mappers.RecipePOJO;
import dao.mappers.UserPOJO;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * The type Data access test.
 *
 * @author Liming Gan
 */
class DataAccessTest {
    /**
     * The Data Access.
     */
    DataAccess da = DataAccess.getInstance();

    /**
     * Gets user test.
     */
    @org.junit.jupiter.api.Test
    void getUser() {
        UserPOJO user1 = new UserPOJO();
        user1.setUserId(1);
        user1.setUserName("liming");
        user1.setPassword("123456");
        UserPOJO user2 = new UserPOJO();
        user2.setUserId(2);
        user2.setUserName("liming");
        user2.setPassword("789");

        da.insertUser(user1);
        da.insertUser(user2);
        List<UserPOJO> list = da.getUser("liming");
        assertEquals(1, list.get(0).getUserId());
        assertEquals(2, list.get(1).getUserId());
    }

    /**
     * Gets recipe owner test.
     */
    @org.junit.jupiter.api.Test
    void getRecipe_Owner() {
        List<RecipePOJO> list = da.getRecipe_Name("hong shao rou");
        assertEquals(94056489, list.get(0).getOwnerId());
    }

    /**
     * Gets recipe by name test.
     */
    @org.junit.jupiter.api.Test
    void getRecipe_Name() {
        List<RecipePOJO> list = da.getRecipe_Name("hong shao rou");
        assertEquals(1 , list.size());
    }

    /**
     * Gets recipe by category test.
     */
    @org.junit.jupiter.api.Test
    void getRecipe_Category() {
        List<RecipePOJO> list = da.getRecipe_Name("hong shao rou");
        assertEquals("Lunch", list.get(0).getRecipeCategory());
    }

    /**
     * Gets recipe all test.
     */
    @org.junit.jupiter.api.Test
    void getRecipe_All() {
        List<RecipePOJO> list = da.getRecipe_All();
        assertEquals(4 , list.size());
    }

    /**
     * Delete recipe test.
     */
    @org.junit.jupiter.api.Test
    void deleteRecipe() {
        List<RecipePOJO> list = da.getRecipe_Name("hong shao rou");
        assertEquals(1, list.size());
        da.deleteRecipe(list.get(0));
        list = da.getRecipe_Name("hong shao rou");
        assertEquals(0, list.size());
    }
}