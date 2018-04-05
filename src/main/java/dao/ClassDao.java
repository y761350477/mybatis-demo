package dao;

import entity.Class;

import java.util.List;
import java.util.Map;

public interface ClassDao {
    List<Class> findAll();

    List<Class> findOneToMany();

    int insert(Class c);

    int delete(Class c);

    int update(Class c);

    Class findBy(Class c);

    Class findString(String a);

    Class findByMap(Map map);
}
