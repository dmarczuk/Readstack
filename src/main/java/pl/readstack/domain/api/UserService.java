package pl.readstack.domain.api;

import pl.readstack.domain.discovery.User;
import pl.readstack.domain.discovery.UserDao;

import java.time.LocalDateTime;

public class UserService {
    private UserDao userDao = new UserDao();

    public void register(UserRegistation userRegistation) {
        User userToSave = UserMapper.map(userRegistation);
        userDao.save(userToSave);
    }

    private static class UserMapper {
        static User map(UserRegistation userRegistation) {
            return new User(
                    userRegistation.getUsername(),
                    userRegistation.getEmail(),
                    userRegistation.getPassword(),
                    LocalDateTime.now()
            );
        }
    }
}
