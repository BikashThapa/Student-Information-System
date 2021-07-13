package com.Bikash.studentinfosys.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Bikash.studentinfosys.dto.Student;
import com.Bikash.studentinfosys.util.DbUtil;

public class studenDaoImpl implements studentDao {

	PreparedStatement ps = null;

	@Override
	public void savestudent(Student student) {

		String sql = " insert into student_tbl(student_name,college_name,email,gender,image_url,subject,department,roll,dob) values(?,?,?,?,?,?,?,?,?)";

		try {
			ps = DbUtil.getConnection().prepareStatement(sql);
			ps.setString(1, student.getStudentName());
			ps.setString(2, student.getCollegeName());
			ps.setString(3, student.getEmail());
			ps.setString(4, student.getGender());
			ps.setString(5, student.getImage_url());
			ps.setString(6, student.getSubject());
			ps.setString(7, student.getDepartments());
			ps.setInt(8, student.getRoll());
			ps.setDate(9, new Date(student.getDob().getTime()));
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Student> getAllstudentInfo() {

		List<Student> studentList = new ArrayList<>();
		String sql = "select * from student_tbl";

		try {
			ps = DbUtil.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Student student = new Student();
				student.setStudentName(rs.getString("student_name"));
				student.setCollegeName(rs.getString("college_name"));
				student.setDepartments(rs.getString("department"));
				student.setEmail(rs.getString("email"));
				student.setSubject(rs.getString("subject"));
				student.setRoll(rs.getInt("roll"));
				student.setId(rs.getInt("id"));
				student.setDob(rs.getDate("dob"));
				student.setGender(rs.getString("gender"));
				studentList.add(student);
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

		return studentList;
	}

	@Override
	public void deleteStudentInfo(int id) {

		String sql = "delete from student_tbl where id=? ";

		try {
			ps = DbUtil.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public Student getStudentById(int id) {

		String sql = "select * from student_tbl where id=?";
		Student student = new Student();
		try {
			ps = DbUtil.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				student.setStudentName(rs.getString("student_name"));
				student.setCollegeName(rs.getString("college_name"));
				student.setDepartments(rs.getString("department"));
				student.setEmail(rs.getString("email"));
				student.setSubject(rs.getString("subject"));
				student.setRoll(rs.getInt("roll"));
				student.setId(rs.getInt("id"));
				student.setDob(rs.getDate("dob"));
				student.setGender(rs.getString("gender"));
				student.setImage_url(rs.getString("image_url"));

			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		return student;
	}

	@Override
	public void updatestudent(Student student) {
		String sql = " update student_tbl set student_name=?,college_name=?,email=?,gender=?,image_url=?,subject=?,department=?,roll=?,dob=? where id=?";

		try {
			ps = DbUtil.getConnection().prepareStatement(sql);
			ps.setString(1, student.getStudentName());
			ps.setString(2, student.getCollegeName());
			ps.setString(3, student.getEmail());
			ps.setString(4, student.getGender());
			ps.setString(5, student.getImage_url());
			ps.setString(6, student.getSubject());
			ps.setString(7, student.getDepartments());
			ps.setInt(8, student.getRoll());
			ps.setDate(9, new Date(student.getDob().getTime()));
			ps.setInt(10, student.getId());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
