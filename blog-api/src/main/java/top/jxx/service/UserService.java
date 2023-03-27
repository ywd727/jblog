package top.jxx.service;

import top.jxx.entity.User;
import top.jxx.model.vo.Result;

public interface UserService {
	User findUserByUsernameAndPassword(String username, String password);

	User findUserById(Long id);

	Result register();

	Result login();
}
