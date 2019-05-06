package dev.fames.mvc.v1;

import dev.fames.mvc.annotation.Service;

@Service("userService")
public class V1UserService {

    public int getAge(String name) {
        if (name.equals("佐助")) {
            return 18;
        } else if (name.equals("鸣人")) {
            return 16;
        } else {
            return 0;
        }

    }

}
