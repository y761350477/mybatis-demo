package dao;

import entity.User;

import java.util.List;

/**
 * Created by YC on 2017/6/19.
 */
public interface UserDao {
    public List<User> findAll();
    public int insert(User user);
    public int delete(User user);
    public int update(User user);
}
