package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class User implements Serializable {

    //之后改一个更好的办法编号 , hashcode? 或是constructor factory？(我们觉着这样最简洁直观，改id没有太大必要）
    private static int counter = 0;
    protected String identity;

    private int userId;
    private String username;
    private String password;
    private String phone;
    private String email;
    private List<Integer> events;
    private Map<User, List<String>> chatHistory;   //确认一下java hashmap 的key 可以mutable?
    //做messenge 功能的人确认什么样子的attributes 好做、


    public User(String username, String password, String phone, String email) {
        this.userId = counter;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.events = new ArrayList<>();
        this.chatHistory = new HashMap<>();
        counter++;

    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //3个角色都要看到自己参与的，organizer看到自己组织的，speaker看到自己要讲的，attendee 看到自己signup的
    public List<Integer> getEvents() {
        return events;
    }

    public void addEvents(OneSpeakerEvent oneSpeakerEvent) {
        this.events.add(oneSpeakerEvent.getId());
    }

    public String getIdentity() {
        return identity;
    }

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", events=" + events +
            ", Identity=" + identity +
            '}';
    }


}



