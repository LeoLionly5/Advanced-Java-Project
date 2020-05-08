package fr.epita.quiz.resources;

import fr.epita.quiz.datamodel.Student;

public class StudentDTO {
	private String name;
	
	public StudentDTO() {
		
	}
	
	public StudentDTO(Student entity) {
		this.name = entity.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
