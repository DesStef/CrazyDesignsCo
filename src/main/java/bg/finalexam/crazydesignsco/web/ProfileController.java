package bg.finalexam.crazydesignsco.web;

import bg.finalexam.crazydesignsco.exception.ObjectNotFoundException;
import bg.finalexam.crazydesignsco.model.dto.user.MyProfileUpdateDTO;
import bg.finalexam.crazydesignsco.model.mapper.UserMapper;
import bg.finalexam.crazydesignsco.model.service.MyProfileServiceModel;
import bg.finalexam.crazydesignsco.service.DesignService;
import bg.finalexam.crazydesignsco.service.UserService;
import bg.finalexam.crazydesignsco.service.impl.DesignServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;


@Controller
public class ProfileController {

    private final UserService userService;
    private final DesignService designService;
    private final UserMapper userMapper;


    public ProfileController(UserService userService, DesignService designService, UserMapper userMapper) {
        this.userService = userService;
        this.designService = designService;
        this.userMapper = userMapper;
    }

    @GetMapping("/profile")
    public String getUserDetail(Principal principal,
                                Model model) {

        Long id = userService.findByEmail(principal.getName()).getId();

        var userDto =
                userService.findUserById(id).
                        orElseThrow(() -> new ObjectNotFoundException("User with ID " +
                                id + " not found!"));

        model.addAttribute("user", userDto);

        return "profile";
    }

    @GetMapping("/profile/me/edit")
    public String editMyProfile(Principal principal,
                                   Model model) {

        Long id = userService.findByEmail(principal.getName()).getId();

        MyProfileServiceModel myProfileServiceModel = userService.findUserByIdReturnMyProfileModel(id).
                        orElseThrow(() -> new ObjectNotFoundException("User with ID " +
                                id + " not found!"));

        MyProfileUpdateDTO myProfileUpdateDTO = userMapper.myProfileServiceModelToMyProfileUpdateDto(myProfileServiceModel);
//        MyProfileUpdateDTO myProfileUpdateDTO = userMapper.userServiceModelToUserUpdateDto(myProfileServiceModel);
        model.addAttribute("myProfileUpdateDto", myProfileUpdateDTO);

        return "profile-edit";
    }

    @PostMapping("/profile/me/edit")
    public String editMyProfile(Principal principal,
                       @Valid MyProfileUpdateDTO myProfileUpdateDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        Long id = userService.findByEmail(principal.getName()).getId();

        if (bindingResult.hasErrors() ||
                userService.existingEmailExceptId(myProfileUpdateDTO.getEmail(), id) != null) {
            redirectAttributes.addFlashAttribute("myProfileUpdateDto", myProfileUpdateDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.myProfileUpdateDto",
                    bindingResult);

            System.out.println(bindingResult.toString());

            if (userService.existingEmailExceptId(myProfileUpdateDTO.getEmail(), id) != null) {
                redirectAttributes.addFlashAttribute("emailFound", true);
            }

            if (!myProfileUpdateDTO.getPassword().equals(myProfileUpdateDTO.getConfirmPassword())) {
                redirectAttributes.addFlashAttribute("noMatch", true);
            }

            System.out.println(new ArrayList<>(bindingResult.getAllErrors()));
            return "redirect:/profile/me/edit";
        }

        MyProfileServiceModel myProfileServiceModel = userMapper.myProfileUpdateDtoToMyProfileServiceModel(myProfileUpdateDTO);
//        MyProfileServiceModel myProfileServiceModel = userMapper.userUpdateDtoToUserServiceModel(myProfileUpdateDTO);
        userService.editMyProfile(id, myProfileServiceModel);

        redirectAttributes.addFlashAttribute("profile", true);

        return "redirect:/profile";
    }

}
