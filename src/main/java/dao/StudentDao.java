package dao;

import java.util.List;

import entity.Student;

public interface StudentDao {
	public List<Student> findAll();
	public int insert(Student s);
	public int delete(int a);
	public int update(Student s);
	public List<Student> findAll1();
}
