package dao.mappers;

import org.apache.ibatis.type.Alias;
import java.io.Serializable;

@Alias("UserPOJO")
public class UserPOJO implements Serializable {
    private int userId;
    private String userName;
    private String password;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
