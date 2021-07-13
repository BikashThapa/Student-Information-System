package com.Bikash.studentinfosys.dao;

import java.util.List;
import com.Bikash.studentinfosys.dto.User;

public interface UserDao {
	
	public void saveUser(User user);
	public List<User> getAllUserInfo();
	public int checkUser(String username,String Password);
	

}
