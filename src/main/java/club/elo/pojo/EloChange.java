package club.elo.pojo;

import lombok.*;

import java.util.Date;

@Value
@Builder(builderClassName = "Builder")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class EloChange {
    private final String name;
    private final Date date;
    private final Double change;
}
