package plt;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.Tolerate;
import plt.jpa.AbstractEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
@JsonPropertyOrder({"email", "name"})
@Builder(builderMethodName = "newPlayer")
@ToString(callSuper = false, of = "email")
@EqualsAndHashCode(callSuper = false, of = "email")
@Table(name = "PLAYERS", uniqueConstraints = @UniqueConstraint(name = "UK_PLAYERS_EMAIL", columnNames = "EMAIL"))
public class Player extends AbstractEntity {

    @Basic
    @NonNull
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Basic
    @NonNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Tolerate
    private Player() {
        super(); // JPA needs this
    }
}
