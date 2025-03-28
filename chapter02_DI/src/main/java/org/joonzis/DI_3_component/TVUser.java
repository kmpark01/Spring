package org.joonzis.DI_3_component;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext ctx = 
				new GenericXmlApplicationContext("applicationContext3.xml");
		
		TV tv = (TV)ctx.getBean("tv");
		tv.PowerOn();
		tv.PowerOff();
		tv.volumeUp();
		tv.volumeDown();
	}

}
