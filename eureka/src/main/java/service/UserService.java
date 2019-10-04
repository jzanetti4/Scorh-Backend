package service;

import org.springframework.stereotype.Service;
import pojo.User;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements ServiceInterface {

    private static Map<Integer, User> map;

    static {
        map = new HashMap<>();
        for (int i = 1; i < 6; i++) {
            map.put(i, new User(i, "test" + i, "pwd" + i));
        }
    }

    @Override
    public User getById(Integer id) {
        return map.get(id);
    }

}
