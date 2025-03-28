package org.joonzis.DI_2_annoConfig;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnoConfig {
	
	@Bean // bean을 만다는 메소드 (human1 이라는 id를 가지는 bean)
	public Person human1() {
		Set<String> hobbies = new HashSet<String>();
		hobbies.add("여행");
		hobbies.add("음주");
		hobbies.add("가무");
		
		Person person = new Person();
		person.setName("김씨");
		person.setAge(20);
		person.setHobbies(hobbies);
		
		return person;
	}
	
	// <bean id="human2" class="org.joonzis.DI_2.Person"></bean>
	@Bean(name = "human2")
	public Person abc() {
		Set<String> hobbies = new HashSet<String>();
		hobbies.add("낚시");
		hobbies.add("골프");
		hobbies.add("볼링");
		
		Person person = new Person();
		person.setName("박씨");
		person.setAge(25);
		person.setHobbies(hobbies);
		
		return person;
	}
	
	@Bean(name = "pInfo")
	public PersonInfo pInfo() {
		Set<String> hobbies = new HashSet<String>();
		hobbies.add("낚시");
		hobbies.add("골프");
		hobbies.add("볼링");
		
		Person person = new Person();
		person.setName("박씨");
		person.setAge(25);
		person.setHobbies(hobbies);
		
		PersonInfo info = new PersonInfo();
		info.setPerson(person);
		
		return info;
	}
}
