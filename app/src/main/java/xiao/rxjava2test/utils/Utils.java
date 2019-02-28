package xiao.rxjava2test.utils;

import java.util.ArrayList;
import java.util.List;

import xiao.rxjava2test.model.ApiUser;
import xiao.rxjava2test.model.User;

public class Utils {

    public static List<ApiUser> getApiUserList() {
        List<ApiUser> userList = new ArrayList<>();
        userList.add(new ApiUser("张三", "w138"));
        userList.add(new ApiUser("李四", "w139"));
        return userList;
    }

    public static List<User> convertApiUserListToUserList(List<ApiUser> apiUsers) {
        List<User> users = new ArrayList<>();
        if (apiUsers == null) {
            return users;
        }
        User user = null;
        for (ApiUser apiUser : apiUsers) {
            if (apiUser != null) {
                user = new User(apiUser.getName(), apiUser.getWorkId());
            }
            users.add(user);
        }
        return users;
    }

    public static List<User> getUserListWhoLovesFootball() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("张三", "w138"));
        userList.add(new User("李四", "w139"));
        return userList;
    }

    public static List<User> getUserListWhoLovesCricket() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("张三", "w138"));
        userList.add(new User("王五", "w140"));
        return userList;
    }

    public static List<User> filterUserWhoLovesBoth(List<User> cricketFans, List<User> footballFans) {
        List<User> userWhoLovesBoth = new ArrayList<User>();
        for (User userCricket : cricketFans) {
            for (User userFootBall : footballFans) {
                if (userCricket.getId().equals(userFootBall.getId())) {
                    userWhoLovesBoth.add(userCricket);
                }
            }
        }
        return userWhoLovesBoth;
    }
}
