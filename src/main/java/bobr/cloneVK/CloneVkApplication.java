package bobr.cloneVK;

import bobr.cloneVK.user.Role;
import bobr.cloneVK.user.User;
import bobr.cloneVK.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CloneVkApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(CloneVkApplication.class, args);

        UserService userService = context.getBean(UserService.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        userService.save(User.builder()
                .login("bobr")
                .firstname("Александр")
                .lastname("Бобрусь")
                .password(passwordEncoder.encode("BobrBobr1"))
                .role(Role.USER)
                .build()
        );

        userService.save(User.builder()
                .login("elbro")
                .firstname("Эльдар")
                .lastname("Янбеков")
                .password(passwordEncoder.encode("ElbroElbro2"))
                .role(Role.USER)
                .build()
        );
    }

}
