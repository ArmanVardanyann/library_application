package application.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data(staticConstructor = "of")
public class FlatRole {

    private final UUID id;

    private final String name;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;


    public FlatRole(UUID id, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public FlatRole(FlatRole role) {
        this.id = role.getId();
        this.name = role.getName();
        this.createdAt = role.getCreatedAt();
        this.modifiedAt = role.getModifiedAt();
    }
}
