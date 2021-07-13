package com.Bikash.studentinfosys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Bikash.studentinfosys.dto.User;
import com.Bikash.studentinfosys.util.DbUtil;

public class UserDaoImpl implements UserDao {
	PreparedStatement ps = null;

	@Override
	public void saveUser(User user) {

		String sql = "insert into user_tbl(user_name,password,email) values(?,?,?)";
		try {
			ps = DbUtil.getConnection().prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public List<User> getAllUserInfo() {
		List<User> userList = new ArrayList<>();
		String sql = "select * from user_tbl";
		try {
			ps = DbUtil.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setId(rs.getInt("id"));
				userList.add(user);

			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

		return userList;
	}

	@Override
	public int checkUser(String username, String Password) {

		String sql = " select * from user_tbl where userName=? and password=?";
		try {
			ps = DbUtil.getConnection().prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, Password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return 1;
			}

		} catch (ClassNotFoundException | SQLException e) {
			return 0;
		}

		return 0;
	}

}
