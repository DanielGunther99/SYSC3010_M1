package serverpi_2;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHandler {

    private Connection path;
    public String db = "jdbc:sqlite:C:/sqlite/data";

    public DatabaseHandler() {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            path = DriverManager.getConnection(db);
        } catch (SQLException e) {
            System.out.println("Failed to create connection with sqlite database");
        }

    }

    /**
     * Initializes both the database (if it doesn't already exist) and a table
     * for the new id
     *
     * @param id - ID of new personnel to be added
     */
    public void initDatabase(int id) {

        try {
            Statement command = path.createStatement();

            String comm = "DROP TABLE IF EXISTS personnel" + id;
            command.execute(comm);

            comm = "CREATE TABLE personnel" + id + "(\n" + " datetime string PRIMARY KEY,\n" + " bpm integer,\n"
                    + " ppm integer,\n" + " latitude float,\n" + " longitude float\n" + ");";
            command.execute(comm);

            comm = "CREATE TABLE IF NOT EXISTS knownIDs(\n id integer PRIMARY KEY);";
            command.execute(comm);

            // Initializes another table to store known IDs (IDs for which a table exists)
            comm = "INSERT INTO knownIDs(id) VALUES(?)";
            PreparedStatement c = path.prepareStatement(comm);
            c.setInt(1, id);
            c.executeUpdate();

            System.out.println("Database " + id + " initialized.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void add(int id, java.sql.Date date, java.sql.Time t, int bpm, int ppm, float latitude, float longitude) {

        String comm = "INSERT INTO personnel" + id + "(datetime, bpm, ppm, latitude, longitude) VALUES(?,?,?,?,?)";

        // An id of -1 indicates that data was not recieved correctly
        if (id != -1) {
            try {
                PreparedStatement command = path.prepareStatement(comm);

                String datetime = date + "_" + t;
                command.setString(1, datetime);
                command.setInt(2, bpm);
                command.setInt(3, ppm);
                command.setFloat(4, latitude);
                command.setFloat(5, longitude);
                command.executeUpdate();
                System.out.println("personnel" + id + " updated successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void outputTable(int id) {
        String comm = "SELECT * FROM personnel" + id;

        try {
            Statement command = path.createStatement();
            ResultSet r = command.executeQuery(comm);

            while (r.next()) {
                System.out.println(r.getTime("time") + "\t"
                        + r.getInt("bpm") + "\t"
                        + r.getInt("ppm") + "\t"
                        + r.getFloat("latitude") + "\t"
                        + r.getFloat("longitude"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
