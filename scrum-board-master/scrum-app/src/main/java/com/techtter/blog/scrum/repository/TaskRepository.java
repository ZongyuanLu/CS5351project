package com.techtter.blog.scrum.repository;

import com.techtter.blog.scrum.model.Task;
import com.techtter.blog.scrum.model.TaskStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    Optional<Task> findByTitle(String title);

    @Query("select t from Task as t where t.status=?1 and t.scrum_id=?2 and t.sprint_num=?3")
    List<Task> findByTaskStatusScrumIdAndSprintNum(TaskStatus status, Long id, Integer num);

    @Query("select t from Task as t where t.status='TODO'")
    List<Task> findAllTODOTask();

    @Query("select t from Task as t where t.status='INPROGRESS'")
    List<Task> findAllDOINGTask();

    @Query("select t from Task as t where t.status='DONE'")
    List<Task> findAllDONETask();

    @Query("select t from Task as t where t.scrum_id=?1")
    List<Task> findTasksByScrumId(Long id);

    @Modifying
    @Query("update Task t set t.sprint_num = ?1 where t.id = ?2")
    void updateSprint_num(Integer num, Long id);
}
