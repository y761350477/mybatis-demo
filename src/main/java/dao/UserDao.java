package dao;

import entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mybatis基于注解的使用
 *  1. mybatis-config, mappers下配置<mapper class="dao.UserDao"/>;
 *  2. dao方法上使用@Select("sql语句);
 *  3. MainApp正常调用;
 *
 * @author YC
 * @create 2018/4/7 23:15.
 */
public interface UserDao {
    @Select("select * from t_user")
    List<User> select_all();
}
