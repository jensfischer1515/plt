package plt.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;
import java.util.UUID;

@Converter(autoApply = false)
public class UUIDAttributeConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        return Optional
                .ofNullable(uuid)
                .map(UUID::toString)
                .orElse(null);
    }

    @Override
    public UUID convertToEntityAttribute(String string) {
        return Optional
                .ofNullable(string)
                .map(UUID::fromString)
                .orElse(null);
    }
}
