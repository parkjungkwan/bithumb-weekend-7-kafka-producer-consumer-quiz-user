package net.zerotodev.api.kafka.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String id;
    private String name;
    private String email;

    @Override
    public String toString() {
        return String.format("%s,%s,%s\n",id,name,email);
    }
}
