package dao;

import java.util.List;

import entity.Class;

public interface ClassDao {
	public List<Class> findAll();
	public List<Class> findAll1();
	public int insert(Class c);
	public int delete(int a);
	public int update(Class c);
	public Class findBy(Class c);
	public Class findString(String a);
}
