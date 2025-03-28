package org.joonzis.DI_1;

public class TVUser {
	public static void main(String[] args) {
		SamsungTV stv = new SamsungTV();
		stv.powerOn();
		stv.powerOff();
		stv.volumUp();
		stv.volumDown();
		
		System.out.println("----------------------");

		LgTV ltv = new LgTV();
		ltv.powerOn();
		ltv.powerOff();
		ltv.volumUp();
		ltv.volumDown();
	}
	
}
