package facade;

import api.UserService;
import domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by Z先生 on 2016/10/28.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    public User getUserById(int id) {
        User user=new User(id);
        return user;
    }
}
