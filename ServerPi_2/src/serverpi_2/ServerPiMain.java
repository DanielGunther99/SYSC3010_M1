package serverpi_2;

import java.net.DatagramPacket;
import java.sql.Connection;
import static java.sql.Date.valueOf;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ServerPiMain {

    private static java.sql.Time time;
    private static ArrayList<Integer> ids = new ArrayList<Integer>();
    private java.sql.Date d;

    public static void main(String[] args) {
        udpReceiver listener = new udpReceiver(2949);
        udpSender sender = new udpSender();
        DatabaseHandler DB = new DatabaseHandler();
        String DatabasePath = DB.db;
        DatagramPacket packet;
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

        for (;;) {
            // Wait for a packet to come in
            listener.recieve();

            // Turn raw string into array of strings containing each data piece
            packet = listener.getPacket();
            String str = new String((packet.getData())).trim();
            String[] data = str.split("_");

            int seqID = -1;
            int id = -1;
            String identifier = "null";

            try {
                // Cast each piece of data to respective types
                seqID = Integer.parseInt(data[0]);
                identifier = data[1];
                id = Integer.parseInt(data[2]);
            } catch (Exception e) {
                System.out.println("data format not recognized");
            }

            java.sql.Date date;

            if (identifier.equals("App")) {

                String comm = "SELECT * FROM personnel" + id;
                System.out.println("2");
                String appOut = "";

                try {
                    Connection path = DriverManager.getConnection(DatabasePath);
                    Statement command = path.createStatement();
                    ResultSet r = null;

                    // This needs to be in a try block in case the ID doesn't exist
                    try {
                        r = command.executeQuery(comm);
                        while (r.next()) {
                            String[] dt = r.getString("datetime").split("_");
                            appOut += (dt[0] + "\t"
                                    + dt[1] + "\t"
                                    + r.getInt("bpm") + "\t"
                                    + r.getInt("ppm") + "\t"
                                    + r.getFloat("latitude") + "\t"
                                    + r.getFloat("longitude") + "\n");
                        }
                    } catch (Exception e) {
                        appOut = "No Data Found";
                    }

                    // Send to app
                    byte[] b = appOut.getBytes();
                    DatagramPacket pack = new DatagramPacket(b, b.length, packet.getAddress(), 2950);
                    sender.sendPacket(pack);
                    System.out.println("App data sent to " + packet.getAddress() + " for id " + id);

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            } else {

                try {
                    // Cast data to respective types
                    date = valueOf(data[3]);
                    time = new java.sql.Time(timeFormat.parse(data[4]).getTime());
                    int bpm = Integer.parseInt(data[5]);
                    int ppm = Integer.parseInt(data[6]);
                    float latitude = Float.parseFloat(data[7]);
                    float longitude = Float.parseFloat(data[8]);

                    if (!ids.contains(id)) {
                        DB.initDatabase(id);
                        ids.add(id);
                    }
                    // Add data to database
                    System.out.println("Adding " + id + date + time + bpm + ppm + latitude + longitude);
                    DB.add(id, date, time, bpm, ppm, latitude, longitude);
                } catch (Exception e) {
                    System.out.println("data format not recognized");
                }

                // Send acknowledgement
                String toSend = seqID + "-" + identifier;
                byte[] b = toSend.getBytes();
                DatagramPacket ack = new DatagramPacket(b, b.length, packet.getAddress(), 2950);
                sender.sendPacket(ack);
            }
        }
    }

}
