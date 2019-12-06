package mockobject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class runTest {

    public static void main(String args[]) {
        MockObject mock = new MockObject(2950, 2949);

        Scanner scn = new Scanner(System.in);
        int success = 0;
        
        for (;;) {
            System.out.println("Type 'test', 'testID', 'testApp', 'testOne', 'local_addr'");
            success = 0;

            String in = scn.nextLine();
            if (in.contentEquals("test")) {
                for (int i = 0; i < 30; i++) {

                    mock.sendData();
                    if (mock.recieveACK()) {
                        System.out.println("Acknowledgment received.");
                        success++;
                    }
                }
                System.out.println(success + "/30 acknowledged packets.");
            } else if (in.contentEquals("testID")) {
                mock.incID();
                System.out.println("new ID " + mock.id);
            } else if (in.contentEquals("testApp")) {
                mock.sendApp();
                mock.recieveData();
            } else if (in.contentEquals("testOne")) {
                mock.sendData();
                if (mock.recieveACK()) {
                    System.out.println("Acknowledgment received.");
                }
            } else if (in.contentEquals("local_addr")) {
                String a = "Failed to get address";
                try {
                    a = InetAddress.getLocalHost().toString();
                } catch (Exception e) {

                }
                System.out.println(a);
            }
        }
        
        // Example of data format
        /**
          String toSend = Integer.toString(mock.getSeqID()) + "_" +
          mock.identifier + "_" + mock.id + "_" + mock.getDate() + "_" +
          mock.getTime() + "_" + Integer.toString(mock.getHR()) + "_" +
          Integer.toString(mock.getGas()) + "_" +
          Float.toString(mock.getLong()) + "_" + Float.toString(mock.getLat());
          System.out.println(toSend);
          * */
    //test
    }
}
