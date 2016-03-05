package plt.campaign;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.Tolerate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import plt.jpa.AbstractEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
@JsonPropertyOrder({"name"})
@Builder(builderMethodName = "newCampaign")
@ToString(callSuper = false, of = "name")
@EqualsAndHashCode(callSuper = false, of = "name")
@Table(name = "CAMPAIGNS", uniqueConstraints = @UniqueConstraint(name = "UK_CAMPAIGNS_NAME", columnNames = "NAME"))
public class Campaign extends AbstractEntity {

    @Basic
    @NonNull
    @NotEmpty
    @Length(max = 255)
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Basic
    @NonNull
    @Column(name = "ACTIVE", nullable = false)
    private boolean active = false;

    @Tolerate
    private Campaign() {
        super(); // JPA needs this
    }
}
