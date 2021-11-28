package com.techtter.blog.scrum.service;

import com.techtter.blog.scrum.model.Task;
import com.techtter.blog.scrum.model.TaskStatus;
import com.techtter.blog.scrum.model.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> getAllTasks();

    Optional<Task> getTaskById(Long id);

    Optional<Task> getTaskByTitle(String title);

    List<Task> getTasksByTaskStatusScrumIdAndSprintNum(TaskStatus status, Long id, Integer num);

    Integer getWorkTodoByScrumIdAndSprintNum(Long id, Integer num);

    List<Task> getTasksByScrumId(Long id);

    Task saveNewTask(TaskDTO taskDTO);

    Task updateTask(Task oldTask, TaskDTO newTaskDTO);

    List<Task> getAllTODOTasks();

    List<Task> getAllDOINGTask();

    List<Task> getAllDONETask();

    void deleteTask(Task task);
}
