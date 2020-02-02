package isamm.yassine.project.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isamm.yassine.project.dto.TaskDto;
import isamm.yassine.project.dto.TaskMapper;
import isamm.yassine.project.dto.Util;
import isamm.yassine.project.model.Difficulty;
import isamm.yassine.project.model.Status;
import isamm.yassine.project.model.Task;
import isamm.yassine.project.repository.TaskRepository;
import isamm.yassine.project.service.TaskService;

@Service
public class mainTaskService implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskMapper taskMapper;

	@Override
	public TaskDto createTask(TaskDto taskDto) {
		Task task = taskMapper.asTask(taskDto);
		taskRepository.save(task);
		return taskMapper.asTaskDto(task);
	}

	@Override
	public void updateTask(TaskDto taskDto) {
		Optional<Task> optional = taskRepository.findById(taskDto.getId());
		if (optional.isPresent()) {
			Task task = optional.get();
			task.setDescription(taskDto.getDescription());
			task.setDeadline(taskDto.getDeadline());
			task.setStatus(Status.valueOf(taskDto.getStatus().name()));
			task.setDifficulty(Difficulty.valueOf(taskDto.getDifficulty().name()));
			task.setName(taskDto.getName());
		
			taskRepository.save(task);
		}
	}

	@Override
	public TaskDto findTaskById(long taskId) {
		Optional<Task> optional = taskRepository.findById(taskId);
		if (optional.isPresent()) {
			return taskMapper.asTaskDto(optional.get());
		}
		return null;
	}

	@Override
	public Iterable<TaskDto> findAllTasks() {
		Iterable<Task> tasks = taskRepository.findAll();
		return taskMapper.asTaskCollectionDTO(Util.toList(tasks));
	}

	@Override
	public void deleteTaskDtoById(long taskId) {
		taskRepository.deleteById(taskId);
	}

}
