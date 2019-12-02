package mockobject;

import java.util.Scanner;

public class runTest {

	public static void main(String args[]) {
		MockObject mock = new MockObject(2050, 2057);
		
		Scanner scn = new Scanner(System.in);
		for (;;) {
			System.out.println("Type 'test' to send mock data");
			String in = scn.nextLine();
			if (in.contentEquals("test")) {
				mock.sendData();
			}
		}
		
	}
}
