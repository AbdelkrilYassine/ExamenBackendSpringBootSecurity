package isamm.yassine.project.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
	
	private Long id;

	private String name;

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date deadline;

	private String description;

	private StatusDto status;
	private DifficultyDto difficulty;
	
}
