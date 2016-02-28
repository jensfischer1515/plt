package plt.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static javax.persistence.AccessType.FIELD;
import static lombok.AccessLevel.NONE;

@Getter
@Setter
@Access(FIELD)
@MappedSuperclass
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity implements Persistable<UUID>, Identifiable<UUID> {

    @Id
    @Setter(NONE)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID", nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Version
    @JsonIgnore
    @Setter(NONE)
    @Column(name = "VERSION", nullable = false)
    private Long version;

    @Basic
    @JsonIgnore
    @Setter(NONE)
    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false, insertable = true, updatable = false)
    private LocalDateTime createdDate;

    @Basic
    @JsonIgnore
    @Setter(NONE)
    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE", nullable = false, insertable = true, updatable = true)
    private LocalDateTime lastModifiedDate;

    @Tolerate
    protected AbstractEntity() {
        // JPA needs this
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isNew() {
        return createdDate == null;
    }
}
