package net.decenternet.technicalexam.data;

import net.decenternet.technicalexam.domain.Task;

import java.util.List;

public interface TaskLocalService {

    void save(Task task);
    void delete(int id);
    void update(Task task);
    List<Task> findAll();

}
