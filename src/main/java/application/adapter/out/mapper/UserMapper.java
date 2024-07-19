package application.adapter.out.mapper;

import application.domain.dto.User;
import application.domain.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity mapToEntity(User user) {

        return new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRoles(),
                user.getAccountBalance(),
                user.getPurchasedBooks(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    public User mapToDomain(UserEntity user) {
        return User.withId(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRoles(),
                user.getAccountBalance(),
                user.getPurchasedBooks(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

}
