package club.elo.pojo;

import lombok.*;

import java.util.Date;

@Value
@Builder(builderClassName = "Builder")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class EloEntry {
    private final String rank;
    private final String clubName;
    private final String country;
    private final Integer levelOfPlay;
    private final Double elo;
    private final Date startDate;
    private final Date endDate;
}
