package com.techtter.blog.scrum.service;

import com.techtter.blog.scrum.model.ScrumUser;
import com.techtter.blog.scrum.model.ScrumUserDTO;

import java.util.Optional;

public interface ScrumUserService {
    Optional<ScrumUser> getScrumUserByScrumId(Long scrum_id);
    ScrumUser saveNewScrumUserByScrumIdUserId(Long scrum_id, String phone);
}
