package bg.finalexam.crazydesignsco.web;

import bg.finalexam.crazydesignsco.exception.ObjectNotFoundException;
import bg.finalexam.crazydesignsco.model.dto.user.UserUpdateDTO;
import bg.finalexam.crazydesignsco.model.enums.UserRoleEnum;
import bg.finalexam.crazydesignsco.model.mapper.UserMapper;
import bg.finalexam.crazydesignsco.model.service.UserServiceModel;
import bg.finalexam.crazydesignsco.service.UserRoleService;
import bg.finalexam.crazydesignsco.service.UserService;
import bg.finalexam.crazydesignsco.service.impl.UserRoleServiceImpl;
import bg.finalexam.crazydesignsco.service.impl.UserServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserRoleService userRoleService, UserMapper userMapper) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.userMapper = userMapper;
    }


    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes
    ) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:/users/login";
    }

    @GetMapping("/all")
    public String allUsers(
            Model model,
            @PageableDefault(
                    sort = "firstName",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 2) Pageable pageable) {

        model.addAttribute("users", userService.getAllUsers(pageable));

        return "users";
    }

    @GetMapping("/profile/{id}")
    public String updateUserDetails(@PathVariable("id") Long id,
                                    Model model) {

        UserServiceModel userServiceModel = userService.findUserByIdReturnUserModel(id).
                orElseThrow(() -> new ObjectNotFoundException("User with ID " +
                        id + " not found!"));
        System.out.println("status 1" + userServiceModel.toString());

        UserUpdateDTO userUpdateDTO = userMapper.userServiceModelToUserUpdateDto(userServiceModel);
        String email = userUpdateDTO.getEmail();
        System.out.println("status 2" + userUpdateDTO.toString());
        model.addAttribute("userUpdateDTO", userUpdateDTO);
//        added from other
        model.addAttribute("userRoles", userService.findUserRolesByEmail(email).toString());
//                .addAttribute("email", email);

        return "profile-user";
    }

    @PostMapping("/profile/{id}")
    public String editUserProfile(@PathVariable("id") Long id,
                       @Valid UserUpdateDTO userUpdateDTO,
                                  @RequestParam String userRole,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

//        Long id = userService.findByEmail(principal.getName()).getId();

        System.out.println("role from UpdateDTO" + userRole);
        System.out.println("Passwords: " + userUpdateDTO.getPassword() + " " + userUpdateDTO.getConfirmPassword());

        if (bindingResult.hasErrors() ||
                userService.existingEmailExceptId(userUpdateDTO.getEmail(), id) != null) {
            redirectAttributes.addFlashAttribute("userUpdateDTO", userUpdateDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userUpdateDTO",
                    bindingResult);

            System.out.println("test if printed: there are errors");
            if (userService.existingEmailExceptId(userUpdateDTO.getEmail(), id) != null) {
                redirectAttributes.addFlashAttribute("emailFound", true);
            }


//            System.out.println(new ArrayList<>(bindingResult.getAllErrors()));
            return "redirect:/users/profile/{id}";
        }

        if (!userUpdateDTO.getPassword().equals(userUpdateDTO.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("passNoMatch", true);

            return "redirect:/users/profile/{id}";
        }
        System.out.println("status 3");

        UserServiceModel userServiceModel = userMapper.userUpdateDtoToUserServiceModel(userUpdateDTO);
        UserRoleEnum userRoleEnum;

        if (userRole != null && !userRole.isEmpty()) {
            userRoleEnum = UserRoleEnum.valueOf(userRole.toUpperCase());
            userServiceModel.setUserRoleEnum(userRoleEnum);
            System.out.println(userServiceModel.getUserRoleEnum());
        }

        userService.editUser(id, userServiceModel);

        redirectAttributes.addFlashAttribute("updatedUserProfile", true)
                .addFlashAttribute("userId", id);

        System.out.println("done editing");
//        redirectAttributes.addFlashAttribute("profile", true);

        return "redirect:/users/all";
    }

//    @PostMapping("/remove")
//    public String removeRole(@RequestParam String email, @RequestParam String role,
//                             RedirectAttributes redirectAttributes) {
//        redirectAttributes.addFlashAttribute("email", email);
//        userService.removeRole(email, role);
//        redirectAttributes.addFlashAttribute("successfullyChangedRole", true);
//        return "redirect:/roles/change";
//    }

//    TODO: Delete not working
//    @PreAuthorize("isAdmin(#principal.getName())")
//    @DeleteMapping("/users/{id}")
//    public String deleteOffer(
//            @PathVariable("id") Long id, Principal principal) {
//        userService.deleteUserById(id);
//
//        return "redirect:/users/all";
//    }

}

