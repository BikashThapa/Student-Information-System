package com.Bikash.studentinfosys.dao;

import java.util.List;

import com.Bikash.studentinfosys.dto.Student;

public interface studentDao {
	
	
	public void savestudent(Student student);
	public List<Student> getAllstudentInfo();
	public void deleteStudentInfo(int id);
	public Student getStudentById(int id);
	public void updatestudent(Student student);
	
}
