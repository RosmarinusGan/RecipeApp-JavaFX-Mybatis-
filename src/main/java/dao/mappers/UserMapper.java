package dao.mappers;

import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * The interface User mapper.
 * Manage the mapper between POJO and xml.
 *
 * @author Liming Gan
 */
public interface UserMapper {
    /**
     * Add user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean addUser(@Param("user") UserPOJO user);

    /**
     * Delete user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean deleteUser(@Param("user") UserPOJO user);

    /**
     * Update user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean updateUser(@Param("user") UserPOJO user);

    /**
     * Gets user by id.
     *
     * @param userId the user id
     * @return the user by id
     */
    public List<UserPOJO> getUserById(@Param("id") int userId);

    /**
     * Gets user by name.
     *
     * @param userName the user name
     * @return the user by name
     */
    public List<UserPOJO> getUserByName(@Param("name") String userName);
}
