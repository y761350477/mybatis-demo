package dao;

import java.util.List;

import entity.Student;

public interface StudentDao {
	public List<Student> findManyToOne();
}
