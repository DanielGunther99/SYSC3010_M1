package mockobject;

import java.util.Scanner;

public class runTest {

	public static void main(String args[]) {
		MockObject mock = new MockObject(2101, 2099, 2100);
		
		Scanner scn = new Scanner(System.in);
		int success = 0;
		for (;;) {
			System.out.println("Type 'test' to send mock data");
			success = 0;
			
			String in = scn.nextLine();
			if (in.contentEquals("test")) {
					for(int i = 0; i <30; i++) {
						
						mock.sendData();
						if(mock.recieveACK()) {
							System.out.println("Acknowledgment received.");
							success++;
						}
						
					}
					System.out.println(success + "/30 acknowledged packets.");
			}
		}
	}
}
