package isamm.yassine.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import isamm.yassine.project.dto.TaskDto;
import isamm.yassine.project.service.TaskService;

@CrossOrigin
@RestController
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping(value = "/tasks/{taskId}")
	public ResponseEntity<?> getTask(@PathVariable Long taskId) {
		TaskDto taskDto = taskService.findTaskById(taskId);
		if (taskDto != null) {
			return new ResponseEntity<>(taskDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/tasks")
	public ResponseEntity<Iterable<TaskDto>> findAllTasks() {
		Iterable<TaskDto> tasks = taskService.findAllTasks();
		return new ResponseEntity<Iterable<TaskDto>>(tasks, HttpStatus.OK);
	}

	@PostMapping("/tasks")
	public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto) {
		taskService.createTask(taskDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/tasks/{taskId}")
	public ResponseEntity<?> updateTask(@RequestBody TaskDto taskDto, @PathVariable Long taskId) {
		System.out.println("taskDto :"+taskDto);
		System.out.println("taskID :"+taskId);
		taskDto.setId(taskId);
		taskService.updateTask(taskDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/tasks/{taskId}")
	public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
		taskService.deleteTaskDtoById(taskId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
