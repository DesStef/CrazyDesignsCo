package bg.finalexam.crazydesignsco.web;

import bg.finalexam.crazydesignsco.exception.ObjectNotFoundException;
import bg.finalexam.crazydesignsco.model.dto.user.MyProfileUpdateDTO;
import bg.finalexam.crazydesignsco.model.mapper.UserMapper;
import bg.finalexam.crazydesignsco.model.service.MyProfileServiceModel;
import bg.finalexam.crazydesignsco.service.impl.DesignServiceImpl;
import bg.finalexam.crazydesignsco.service.impl.UserServiceImpl;
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

    private final UserServiceImpl userServiceImpl;
    private final DesignServiceImpl designServiceImpl;
    private final UserMapper userMapper;


    public ProfileController(UserServiceImpl userServiceImpl, DesignServiceImpl designServiceImpl, UserMapper userMapper) {
        this.userServiceImpl = userServiceImpl;
        this.designServiceImpl = designServiceImpl;
        this.userMapper = userMapper;
    }

    @GetMapping("/profile")
    public String getUserDetail(Principal principal,
                                Model model) {

        Long id = userServiceImpl.findByEmail(principal.getName()).getId();

        var userDto =
                userServiceImpl.findUserById(id).
                        orElseThrow(() -> new ObjectNotFoundException("User with ID " +
                                id + " not found!"));

        model.addAttribute("user", userDto);

        return "profile";
    }

    @GetMapping("/profile/me/edit")
    public String editMyProfile(Principal principal,
                                   Model model) {

        Long id = userServiceImpl.findByEmail(principal.getName()).getId();

        MyProfileServiceModel myProfileServiceModel = userServiceImpl.findUserByIdReturnMyProfileModel(id).
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

        Long id = userServiceImpl.findByEmail(principal.getName()).getId();

        if (bindingResult.hasErrors() ||
                userServiceImpl.existingEmailExceptId(myProfileUpdateDTO.getEmail(), id) != null) {
            redirectAttributes.addFlashAttribute("myProfileUpdateDto", myProfileUpdateDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.myProfileUpdateDto",
                    bindingResult);

            System.out.println(bindingResult.toString());

            if (userServiceImpl.existingEmailExceptId(myProfileUpdateDTO.getEmail(), id) != null) {
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
        userServiceImpl.editMyProfile(id, myProfileServiceModel);

        redirectAttributes.addFlashAttribute("profile", true);

        return "redirect:/profile";
    }

}
