package org.mytoypjt.service;

import lombok.AllArgsConstructor;
import org.mytoypjt.dao.ProfileDao;
import org.mytoypjt.exception.CustomException;
import org.mytoypjt.exception.ErrorCode;
import org.mytoypjt.models.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileDao profileDao;

    public Profile getProfile(int accountNo) {
        List<Profile> profiles = profileDao.findByAccountNo(accountNo);
        if (profiles == null || profiles.isEmpty()) {
            throw new CustomException(ErrorCode.PROFILE_IS_NOT_EXIST);
        }
        return profiles.get(0);
    }

    @Transactional
    public Profile createDefaultProfile(int accountNo) {
        return profileDao.createProfile(new Profile(accountNo));
    }
}
