package dao;

import entity.Class;
import org.apache.ibatis.annotations.Param;

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

    List<Class> selectByArray(int[] array_int);

    int deleteByArray(int[] stunos);

    List<Class> selectByArrayList(List list);

    List<Class> selectByHashMap(@Param("params")Map map);

    int insertByHashMap(@Param("params") Map map);

    List<Class> select_if(Class class_if);

    List<Class> select_choose(Class class_choose);

    List<Class> select_where(Class class_where);

    List<Class> select_trim(Class class_trim);

    int update_set(Class class_set);

    int update_trim(Class class_trim);
}
