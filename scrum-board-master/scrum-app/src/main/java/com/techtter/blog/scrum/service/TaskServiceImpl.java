package com.techtter.blog.scrum.service;

import com.techtter.blog.scrum.model.Task;
import com.techtter.blog.scrum.model.TaskStatus;
import com.techtter.blog.scrum.model.TaskDTO;
import com.techtter.blog.scrum.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public List<Task> getAllTasks() {
        List<Task> tasksList = new ArrayList<>();
        taskRepository.findAll().forEach(tasksList::add);
        return tasksList;
    }

    @Override
    @Transactional
    public List<Task> getAllTODOTasks(){
        List<Task> tasksTODOList = new ArrayList<>();
        taskRepository.findAllTODOTask().forEach(tasksTODOList::add);
        return tasksTODOList;
    }

    @Override
    public List<Task> getAllDOINGTask() {
        List<Task> tasksDOINGList = new ArrayList<>();
        taskRepository.findAllDOINGTask().forEach(tasksDOINGList::add);
        return tasksDOINGList;
    }

    @Override
    public List<Task> getAllDONETask(){
        List<Task> tasksDONEList = new ArrayList<>();
        taskRepository.findAllDONETask().forEach(tasksDONEList::add);
        return tasksDONEList;
    }

    @Override
    @Transactional
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Task> getTaskByTitle(String title) {
        return taskRepository.findByTitle(title);
    }

    @Override
    @Transactional
    public List<Task> getTasksByTaskStatusScrumIdAndSprintNum(TaskStatus status, Long id, Integer num) {
        return taskRepository.findByTaskStatusScrumIdAndSprintNum(status, id, num);
    }

    @Override
    @Transactional
    public Integer getWorkTodoByScrumIdAndSprintNum(Long id, Integer num) {
        int work = 0;
        List<Task> TODO_INPROGRESS = getToDoingByScrumIdAndSprintNum(id, num);
        for (Task t: TODO_INPROGRESS) {
            Integer value;
            if (t.getWorkload() != null) {
                if (t.getProgress() == null) {
                    value = t.getWorkload();
                } else {
                    value = t.getWorkload() - t.getProgress();
                }
                if (value > 0 && value <= t.getWorkload()) {
                    work += value;
                }
            }
        }
        return work;
    }

    @Override
    @Transactional
    public List<Task> getTasksByScrumId(Long id) {
        return taskRepository.findTasksByScrumId(id);
    }

    @Override
    @Transactional
    public Task saveNewTask(TaskDTO taskDTO) {
        return taskRepository.save(convertDTOToTask(taskDTO));
    }

    @Override
    @Transactional
    public Task updateTask(Task oldTask, TaskDTO newTaskDTO) {
        Task t = taskRepository.save(updateTaskFromDTO(oldTask, newTaskDTO));
        Long id = oldTask.getScrum_id();
        Integer num = newTaskDTO.getSprint_num();
        if (checkSprintFinish(id, num)) {
            List<Task> TODO_INPROGRESS = getToDoingByScrumIdAndSprintNum(id, num);
            for (Task task: TODO_INPROGRESS) {
                taskRepository.updateSprint_num(task.getSprint_num()+1, task.getId());
            }
        }
        return t;
    }

    @Override
    @Transactional
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    private List<Task> getToDoingByScrumIdAndSprintNum(Long id, Integer num) {
        List<Task> TODO = taskRepository.findByTaskStatusScrumIdAndSprintNum(TaskStatus.TODO, id, num);
        List<Task> INPROGRESS = taskRepository.findByTaskStatusScrumIdAndSprintNum(TaskStatus.INPROGRESS, id, num);
        List<Task> TODO_INPROGRESS = new ArrayList<>();
        Optional.ofNullable(TODO).ifPresent(TODO_INPROGRESS::addAll);
        Optional.ofNullable(INPROGRESS).ifPresent(TODO_INPROGRESS::addAll);
        return TODO_INPROGRESS;
    }

    private Boolean checkSprintFinish(Long id, Integer num) {
        int threshold = 10;
        int progress = 0;
        List<Task> list = taskRepository.findByTaskStatusScrumIdAndSprintNum(TaskStatus.DONE, id, num);
        if (!list.isEmpty()) {
            for (Task t : list) {
                progress += t.getWorkload();
            }
        }
        return threshold < progress;
    }

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private Date parseDate(String date) {
        try {
            return new Date(DATE_FORMAT.parse(date).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Task convertDTOToTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setColor(taskDTO.getColor());
        task.setStatus(taskDTO.getStatus());
        task.setSprint_num(taskDTO.getSprint_num());
        task.setWorkload(taskDTO.getWorkload());
        task.setProgress(taskDTO.getProgress());
        task.setComplete_date(parseDate(taskDTO.getComplete_date()));
        task.setUser_id(taskDTO.getUser_id());
        return task;
    }

    private Task updateTaskFromDTO(Task task, TaskDTO taskDTO){
        if(Optional.ofNullable(taskDTO.getTitle()).isPresent()){
            task.setTitle(taskDTO.getTitle());
        }

        if (Optional.ofNullable((taskDTO.getDescription())).isPresent()) {
            task.setDescription(taskDTO.getDescription());
        }

        if (Optional.ofNullable((taskDTO.getColor())).isPresent()) {
            task.setColor(taskDTO.getColor());
        }

        if (Optional.ofNullable((taskDTO.getStatus())).isPresent()) {
            task.setStatus(taskDTO.getStatus());
        }

        if (Optional.ofNullable((taskDTO.getSprint_num())).isPresent()) {
            task.setSprint_num(taskDTO.getSprint_num());
        }

        if (Optional.ofNullable(((taskDTO.getWorkload()))).isPresent()) {
            task.setWorkload(taskDTO.getWorkload());
        }

        if (Optional.ofNullable(((taskDTO.getProgress()))).isPresent()) {
            task.setProgress(taskDTO.getProgress());
        }

        if (Optional.ofNullable(((taskDTO.getComplete_date()))).isPresent()) {
            task.setComplete_date(parseDate(taskDTO.getComplete_date()));
        }

        return task;
    }
}
