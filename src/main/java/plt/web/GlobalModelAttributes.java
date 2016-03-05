package plt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import plt.BuildInfoSettings;

@ControllerAdvice
public class GlobalModelAttributes {

    @Autowired
    private BuildInfoSettings buildInfoSettings;

    @ModelAttribute("buildInfoSettings")
    public BuildInfoSettings populateBuildInfoSettings() {
        return buildInfoSettings;
    }
}
