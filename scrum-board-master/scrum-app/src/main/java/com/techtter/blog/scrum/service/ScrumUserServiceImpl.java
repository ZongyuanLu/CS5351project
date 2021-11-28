package com.techtter.blog.scrum.service;

import com.techtter.blog.scrum.model.ScrumUser;
import com.techtter.blog.scrum.model.ScrumUserDTO;
import com.techtter.blog.scrum.model.User;
import com.techtter.blog.scrum.repository.ScrumUserRepository;
import com.techtter.blog.scrum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScrumUserServiceImpl implements ScrumUserService {

    private final ScrumUserRepository scrumUserRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Optional<ScrumUser> getScrumUserByScrumId(Long scrum_id) {
        return scrumUserRepository.findByScrumId(scrum_id);
    }

    @Override
    @Transactional
    public ScrumUser saveNewScrumUserByScrumIdUserId(Long scrumId, String phone) {
        Optional<User> optionalUser = userRepository.findUserIdByPhone(phone);
        int id = optionalUser.get().getId();
        return scrumUserRepository.save(convertDTOToScrumUser(scrumId, id));
    }
    private ScrumUser convertDTOToScrumUser(Long scrumId, int user_id){
        ScrumUser scrum_user = new ScrumUser();
        scrum_user.setScrumId(scrumId);
        scrum_user.setUserId(user_id);
        //team member
        scrum_user.setRoleId(0);
        return scrum_user;
    }
}
