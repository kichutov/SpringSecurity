package com.kichutov.app.service;


import com.kichutov.app.dto.AppUserDto;

public interface RegistrationService {
    void validateAppUserDto(AppUserDto appUserDto);

    void registerNewAppUserAccount(AppUserDto appUserDto);


}
