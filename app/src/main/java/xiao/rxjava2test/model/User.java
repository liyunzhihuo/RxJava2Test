package xiao.rxjava2test.model;

public class User {
    private String nickname;
    private String id;

    public User(){}
    public User(String nickname,String id){
        this.nickname=nickname;
        this.id=id;
    }
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
