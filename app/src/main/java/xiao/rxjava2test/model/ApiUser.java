package xiao.rxjava2test.model;

public class ApiUser {
    private String name;
    private String workId;

    public ApiUser() {
    }

    public ApiUser(String name, String workId) {
        this.name = name;
        this.workId = workId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }
}
