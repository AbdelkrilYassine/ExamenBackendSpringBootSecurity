package isamm.yassine.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isamm.yassine.project.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {

}
