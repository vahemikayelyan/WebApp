package com.springapp.mvc.controller;

import com.springapp.mvc.model.User;
import com.springapp.mvc.model.UserProfile;
import com.springapp.mvc.model.UserProfileType;
import com.springapp.mvc.service.UserNotificationService;
import com.springapp.mvc.service.UserProfileService;
import com.springapp.mvc.service.UserService;
import com.springapp.mvc.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * Created by VAHE on 06-Dec-16.
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserNotificationService userNotificationService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage() {
        return "home";
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "redirect:/backend/list";
        } else {
            return "login";
        }
    }

    /**
     * This method handles logout requests.
     * Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !authenticationTrustResolver.isAnonymous(authentication);
    }

    @RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
    public String signupUser(ModelMap model) {
        if (isCurrentAuthenticationAnonymous()) {
            return "redirect:/home";
        }
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result, WebRequest request, ModelMap model) {
        //userValidator.validate(user, result);
        Set<UserProfile> userProfiles = user.getUserProfiles();
        userProfiles.add(userProfileService.findByType(UserProfileType.USER.name()));

        if (result.hasErrors()) {
            return "signup";
        }

		/*
         * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation
		 * and applying it on field [sso] of Model class [User].
		 *
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 *
		 */
        if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
            FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "signup";
        }

        // Save user to database
        userService.saveUser(user);

        // Send a notification
        try {
            userNotificationService.sendNotification(user);
            model.addAttribute("success", "User " + user.getSsoId() + " registered successfully");
        } catch (Exception e) {
            // catch error
            model.addAttribute("success", "Error Sending Email: " + e.getMessage());
        }

        return "registrationSuccess";
    }

    @ResponseBody
    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public void registrationConfirm(@RequestParam("token") String token) {
        User user;

        try {
            Date date = new Date();
            user = userService.findBySSO(token);
            user.setEnabled(true);
            user.setConfirmDate(new Timestamp(date.getTime()));
            userService.updateUser(user);
        } catch (NullPointerException ex) {
            ex.getMessage();
        }
    }
}