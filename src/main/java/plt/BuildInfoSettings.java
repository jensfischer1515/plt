package plt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "info.build")
public class BuildInfoSettings {
    private String groupId;
    private String artifactId;
    private String version;
    private String name;
    private String description;
}
