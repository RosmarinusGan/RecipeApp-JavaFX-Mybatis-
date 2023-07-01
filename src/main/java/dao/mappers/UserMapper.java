package dao.mappers;

import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserMapper {
    public boolean addUser(@Param("user") UserPOJO user);
    public boolean deleteUser(@Param("user") UserPOJO user);
    public boolean updateUser(@Param("user") UserPOJO user);
    public List<UserPOJO> getUserById(@Param("id") int userId);
    public List<UserPOJO> getUserByName(@Param("name") String userName);
}
