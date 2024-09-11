package com.roconmachine.io.parent;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public class Account {
    record Data(
        @Id @NotNull() long id,
        @NotNull String name
    ){

    }
}
