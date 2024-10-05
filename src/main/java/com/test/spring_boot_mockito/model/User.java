package com.test.spring_boot_mockito.model;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {
	@Id
	private long id;

	@Column(updatable = false,unique = true,nullable = false)
	private String email;
	private String name;
	private int age;
	private String address;

}
