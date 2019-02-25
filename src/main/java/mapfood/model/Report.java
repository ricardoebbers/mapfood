package mapfood.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document
public class Report {

    @Id
    String id;
    Restaurant restaurant;
    String distanceToRestaurant;
    String durationToRestaurant;
    String distanceToClient;
    String durationToClient;
    LocalDate date;

}
