package org.joonzis.DI_2_annoConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PersonInfoMain {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AnnoConfig.class);
		
		Person person1 = ctx.getBean("human1", Person.class);
		
		System.out.println("이름 : " + person1.getName());
		System.out.println("이름 : " + person1.getAge());
		System.out.println("이름 : " + person1.getHobbies());
		
		System.out.println("-------------------------------");
		
		Person person2 = ctx.getBean("human2", Person.class);
		
		System.out.println("이름 : " + person2.getName());
		System.out.println("이름 : " + person2.getAge());
		System.out.println("이름 : " + person2.getHobbies());
		
		System.out.println("-------------------------------");
		
		PersonInfo info = ctx.getBean("pInfo", PersonInfo.class);
		info.info();
	}

}
