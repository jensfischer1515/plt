package plt.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    private CampaignRepository campaignRepository;

    @RequestMapping(value = {"", "/"}, method = GET)
    public String list(Model model, Pageable pageable) {
        model.addAttribute("campaigns", campaignRepository.findAll(pageable));
        return "campaigns/list";
    }

    @RequestMapping(value = "/create", method = GET)
    public String form() {
        return "campaigns/create";
    }

    @RequestMapping(value = "/create", method = POST)
    public String create(@Valid final Campaign campaign, final BindingResult binding, RedirectAttributes redirectAttributes) {
        if (binding.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.campaign", binding);
            redirectAttributes.addFlashAttribute("campaign", campaign);
            return "redirect:/campaigns/create";
        }

        Campaign newCampaign = campaignRepository.saveAndFlush(campaign);
        redirectAttributes.addAttribute("campaign", newCampaign);
        return "redirect:/campaigns";
    }
}
