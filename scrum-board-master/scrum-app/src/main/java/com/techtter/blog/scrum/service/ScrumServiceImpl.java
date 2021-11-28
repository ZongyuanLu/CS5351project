package com.techtter.blog.scrum.service;

import com.techtter.blog.scrum.model.*;
import com.techtter.blog.scrum.repository.ScrumRepository;
import com.techtter.blog.scrum.repository.ScrumUserRepository;
import com.techtter.blog.scrum.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ScrumServiceImpl implements ScrumService {

    private final ScrumRepository scrumRepository;
    private final ScrumUserRepository scrumUserRepository;
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public List<Scrum> getAllScrumBoards() {
        List<Scrum> scrumList = new ArrayList<>();
        scrumRepository.findAll().forEach(scrumList::add);
        return scrumList;
    }

    @Override
    @Transactional
    public Optional<Scrum> getScrumById(Long id) {
        return scrumRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Scrum> getScrumByTitle(String title) {
        return scrumRepository.findByTitle(title);
    }

    @Override
    @Transactional
    public Scrum saveNewScrum(ScrumDTO scrumDTO) {
        return scrumRepository.save(convertDTOToScrum(scrumDTO));
    }
//
//    @Override
//    @Transactional
//    public Scrum saveNewScrumWithUserId(int user_id, ScrumDTO scrumDTO) {
//        //scrumUserRepository.save(convertDTOToScrumUser(scrumDTO.getId(), user_id));
//        return scrumRepository.save(convertDTOToScrum(scrumDTO));
//    }

    @Override
    @Transactional
    public Scrum saveNewScrumWithUserId(int user_id, ScrumDTO scrumDTO) {
        ScrumUser su = saveNewScrumUser(user_id, scrumDTO);
        //scrumUserRepository.save(convertDTOToScrumUser(scrumDTO.getId(), user_id));
        return scrumRepository.save(convertDTOToScrum(scrumDTO));
    }

    private ScrumUser saveNewScrumUser(int user_id, ScrumDTO scrumDTO){
        return scrumUserRepository.save(convertDTOToScrumUser(scrumDTO.getId(), user_id));
    }


    private ScrumUser convertDTOToScrumUser(Long scrum_id, int user_id){
        ScrumUser scrum_user = new ScrumUser();
        scrum_user.setScrumId(scrum_id);
        scrum_user.setUserId(user_id);
        //owner
        scrum_user.setRoleId(2);
        return scrum_user;
    }

    @Override
    @Transactional
    public Scrum updateScrum(Scrum oldScrum, ScrumDTO newScrumDTO) {
        oldScrum.setTitle(newScrumDTO.getTitle());
        oldScrum.setAuthor(newScrumDTO.getAuthor());
        oldScrum.setDuration(newScrumDTO.getDuration());
        return scrumRepository.save(oldScrum);
    }

    @Override
    @Transactional
    public void deleteScrum(Scrum scrum) {
        scrumRepository.delete(scrum);
    }

    @Override
    @Transactional
    public Scrum addNewTaskToScrum(Long scrumId, Integer user_id, TaskDTO taskDTO) {
        Scrum scrum = scrumRepository.findById(scrumId).get();
        List<Task> lstTask = taskRepository.findTasksByScrumId(scrumId);
        int max_sprint = 1;
        if (!lstTask.isEmpty()) {
            for (Task t: lstTask) {
                if (t.getSprint_num() != null && t.getSprint_num() > max_sprint) {
                    max_sprint = t.getSprint_num();
                }
            }
        }
        taskDTO.setSprint_num(max_sprint);
        taskDTO.setUser_id(user_id);
        scrum.addTask(convertDTOToTask(taskDTO));
        return scrumRepository.save(scrum);
    }

    private Scrum convertDTOToScrum(ScrumDTO scrumDTO){
        Scrum scrum = new Scrum();
        scrum.setTitle(scrumDTO.getTitle());
        scrum.setAuthor(scrumDTO.getAuthor());
        scrum.setDuration(scrumDTO.getDuration());
        return scrum;
    }

    private Task convertDTOToTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setColor(taskDTO.getColor());
        task.setStatus(taskDTO.getStatus());
        task.setUser_id(taskDTO.getUser_id());
        task.setSprint_num(taskDTO.getSprint_num());
        return task;
    }
}
