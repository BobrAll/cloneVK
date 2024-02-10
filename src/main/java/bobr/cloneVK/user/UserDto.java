package bobr.cloneVK.user;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String login;
    private String firstname;
    private String lastname;
    private String patronymic;

    public UserDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.patronymic = user.getPatronymic();
    }
}
