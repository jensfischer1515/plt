package plt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Tolerate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static javax.persistence.AccessType.FIELD;

@Data
@Builder
@Entity
@Access(FIELD)
@Table(name = "PLAYERS", uniqueConstraints = @UniqueConstraint(name = "UK_PLAYERS_EMAIL", columnNames = "EMAIL"))
@EntityListeners(AuditingEntityListener.class)
public class Player implements Persistable<UUID>, Identifiable<UUID> {

    @Id
    @Column(name = "ID", nullable = false)
    //@Type(type = "pg-uuid")
    private UUID id;

    @Basic
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Basic
    @Column(name = "NAME", nullable = false)
    private String name;


    @Version
    @Column(name = "VERSION", nullable = false)
    @Getter
    @JsonIgnore
    private Long version;

    @Basic
    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false, insertable = true, updatable = false)
    @Getter
    @JsonIgnore
    private LocalDateTime createdDate;

    @Basic
    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE", nullable = false, insertable = true, updatable = true)
    @Getter
    @JsonIgnore
    private LocalDateTime lastModifiedDate;

    @Tolerate
    private Player() {
        // JPA needs this
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }

}
