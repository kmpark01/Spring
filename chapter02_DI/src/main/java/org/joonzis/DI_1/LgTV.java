package org.joonzis.DI_1;

public class LgTV {
	public LgTV() {
		System.out.println("===> LgTV 객체 생성");
	}
	
	public void powerOn() {
		System.out.println("LgTV -- 전원 on");
	}
	
	public void powerOff() {
		System.out.println("LgTV -- 전원off");
	}
	
	public void volumUp() {
		System.out.println("LgTV -- 소리up");
	}
	
	public void volumDown() {
		System.out.println("LgTV -- 소리down");
	}
}
