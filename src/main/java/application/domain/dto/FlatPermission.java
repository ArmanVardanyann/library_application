package application.domain.dto;


import lombok.Data;

import java.util.UUID;

@Data(staticConstructor = "of")
public class FlatPermission {

    private final UUID id;

    private final String code;

    private final String name;

    public FlatPermission(UUID id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
}
