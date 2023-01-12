package org.mytoypjt.service;

import org.mytoypjt.dao.AccountDao;
import org.mytoypjt.dao.ProfileDao;
import org.mytoypjt.models.dto.AccountCertDTO;
import org.mytoypjt.models.entity.Account;
import org.mytoypjt.models.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    MailService mailService;

    public enum CertErrorType {
        isNull, notInput, notSame, good
    }

    public RegisterService() {
    }

    @Transactional
    public RegistrationCertDTO createRegistrationCertDTO(RegistrationRequestDTO dto) {
        if (!accountService.isIdUsing(dto.getId())) {
            throw new CustomException(ErrorCode.ID_IS_USING);
        }
        if (!dto.isPasswordUsable()) {
            throw new CustomException(ErrorCode.PASSWORD_CHECK_FAILURE);
        }
        RegistrationCertDTO certDTO = RegistrationCertDTO.builder()
                .registrationRequestDTO(dto)
                .certValue(getRandomValue())
                .build();
        mailService.sendCertMail(dto.getEmailAddress(), certDTO.getCertValue());
        return certDTO;
    }

    public String getRandomValue(){
        String value = Integer.toString((int)(Math.random() * 1000000));
        return value;
    }

    @Transactional
    public Profile register(RegistrationCertDTO certDTO) {
        int accountNo = accountService.createAccount(certDTO.getRegistrationRequestDTO());
        return profileService.createDefaultProfile(accountNo);
    }

    public boolean isCorrectPw(String pw, String pwCheck){
        if (pw == null || pwCheck == null) return false;
        if (!pw.equals(pwCheck)) return false;
        return true;
    public void updateProfile(Profile profile, Profile inputProfile) {
        inputProfile.checkConvention();
        profile.convert(inputProfile);
        profileService.updateProfile(profile);
    }



}
