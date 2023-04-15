package bg.finalexam.crazydesignsco.web;

import bg.finalexam.crazydesignsco.exception.ObjectNotFoundException;
import bg.finalexam.crazydesignsco.model.dto.design.*;
import bg.finalexam.crazydesignsco.model.user.DesignCoUserDetails;
import bg.finalexam.crazydesignsco.model.view.DesignsHighlightViewModel;
import bg.finalexam.crazydesignsco.service.DesignService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@Controller
public class DesignController {

    private final DesignService designService;

    public DesignController(DesignService designService) {
        this.designService = designService;
    }

    @GetMapping("/designs/all")
    public String allDesigns(
            Model model,
            @PageableDefault(
                    sort = "date",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 5) Pageable pageable) {

        Page<DesignsHighlightViewModel> allDesigns = designService.getAllDesigns(pageable);

        model.addAttribute("designs", allDesigns);

        return "designs";
    }

    @GetMapping("/designs/my")
    public String myDesigns(
            Model model,
            @PageableDefault(
                    sort = "date",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 5) Pageable pageable,
            @AuthenticationPrincipal DesignCoUserDetails userDetails) {

        Page<DesignsHighlightViewModel> allDesigns = designService.getAllDesignsByUser(pageable, userDetails);
        long count = allDesigns.stream().count();

        if (count == 0) {
            model.addAttribute("count", true);
        }

        model.addAttribute("designs", allDesigns);

        return "my-designs";
    }

    @GetMapping("/designs/room-type/other")
    public String designsWithRoomTypeOther(
            Model model,
            @PageableDefault(
                    sort = "date",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 5) Pageable pageable) {

        Page<DesignsHighlightViewModel> allDesigns = designService.getAllDesignsByRoomTypeOther(pageable);
        long count = allDesigns.stream().count();

        if (count == 0) {
            model.addAttribute("count", true);
        }

        model.addAttribute("designsRoomTypeOther", allDesigns);

        return "other-rt-designs";
    }

    @GetMapping("/designs/style/other")
    public String designsWithStyleOther(
            Model model,
            @PageableDefault(
                    sort = "date",
                    direction = Sort.Direction.DESC,
                    page = 0,
                    size = 5) Pageable pageable) {

        Page<DesignsHighlightViewModel> allDesigns = designService.getAllDesignsByStyleOther(pageable);
        long count = allDesigns.stream().count();

        if (count == 0) {
            model.addAttribute("count", true);
        }

        model.addAttribute("designsStyleOther", allDesigns);

        return "other-style-designs";
    }

    @GetMapping("/designs/add")
    public String addDesign(Model model) {
        if (!model.containsAttribute("addDesignModel")) {
            model.addAttribute("addDesignModel", new CreateDesignDTO());
        }

        return "design-add";
    }

    @PostMapping("/designs/add")
    public String addDesign(@Valid CreateDesignDTO createDesignDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            @AuthenticationPrincipal DesignCoUserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addDesignModel", createDesignDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addDesignModel",
                    bindingResult);

            return "redirect:/designs/add";
        }

        designService.addDesign(createDesignDTO, userDetails);

        return "redirect:/designs/all";
    }

    @GetMapping("/designs/search")
    public String searchQuery(@Valid SearchDesignDTO searchDesignDTO,
                              BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchDesignModel", searchDesignDTO);
            model.addAttribute(
                    "org.springframework.validation.BindingResult.searchDesignModel",
                    bindingResult);
            return "design-search";
        }

        if (!model.containsAttribute("searchDesignModel")) {
            model.addAttribute("searchDesignModel", searchDesignDTO);
        }

        if (!searchDesignDTO.isEmpty()) {
            model.addAttribute("designs", designService.searchDesign(searchDesignDTO));
        }

        return "design-search";
    }

    @PreAuthorize("isOwner(#uuid)")
    @GetMapping("/designs/{id}/edit")
    public String edit(@PathVariable("id") UUID uuid,
                       Model model) {
        var updateDesignDTO = designService.getDesignAndEditDetails(uuid);
//                orElseThrow(() -> new ObjectNotFoundException("Design with ID "+ uuid + "not found"));

        model.addAttribute("editDesign", updateDesignDTO);

        return "design-edit";
    }

    @PreAuthorize("isOwner(#uuid)")
    @PostMapping("/designs/{id}/edit")
    public String edit(@PathVariable("id") UUID uuid,
                       @Valid UpdateDesignDTO updateDesignDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       @AuthenticationPrincipal DesignCoUserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addDesignModel", updateDesignDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addDesignModel",
                    bindingResult);

            return "redirect:/designs/{id}/edit";
        }

        designService.editDesign(uuid, updateDesignDTO, userDetails);
        redirectAttributes.addFlashAttribute("designUpdated", true);

        return "redirect:/designs/{id}";
    }

    //    @PreAuthorize("@designService.isOwner(#principal.name, #uuid)")
    @PreAuthorize("isOwner(#uuid)")
    @DeleteMapping("/designs/{id}")
    public String deleteOffer(
            @PathVariable("id") UUID uuid) {
        designService.deleteDesignById(uuid);

        return "redirect:/designs/all";
    }

    @GetMapping("/designs/{id}")
    public String getDesignDetail(@PathVariable("id") UUID uuid,
                                  Model model, Principal principal) {

        var designDto =
                designService.findDesignByUUID(uuid).
                        orElseThrow(() -> new ObjectNotFoundException("Design with UUID " +
                                uuid + " not found!"));

        if (designService.isOwner(principal.getName(), designDto.getId())) {
            model.addAttribute("isOwner", true);
        }

        model.addAttribute("design", designDto);

        return "design-details";
    }
}
