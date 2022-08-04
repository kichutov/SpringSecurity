package com.kichutov.app.controller;

import com.kichutov.app.dto.AppUserDto;
import com.kichutov.app.service.RegistrationService;
import com.kichutov.app.exception.AppUserRegistrationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthController {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final RegistrationService registrationService;

    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @GetMapping("login")
    public String getLoginPage() {
        return "login";
    }


    @GetMapping("registration")
    public String getRegistrationPage(Model model) {
        AppUserDto appUserDto = new AppUserDto();
        model.addAttribute("appUserDto", appUserDto);
        return "registration";
    }


    @PostMapping("registration")
    public String postRegistrationPage(AppUserDto appUserDto, Model model) {
        LOGGER.info("Отправка формы регистрации: {}", appUserDto);
        try {
            registrationService.validateAppUserDto(appUserDto);
            registrationService.registerNewAppUserAccount(appUserDto);
            model.addAttribute("message", "Аккаунт успешно создан");
        }
        catch (AppUserRegistrationException exc) {
            model.addAttribute("error", exc.getMessage());
            return "registration";
        }
        return "login";
    }

}
