package epmduijfx;


import java.sql.*;
import java.time.LocalTime;

public class DatabaseHandler {

	private Connection path;
	
	public DatabaseHandler() {
		// Constructor
	}
	
	/**
	 * Initializes both the database (if it doesn't already exist) and a table for the new id
	 * @param id - ID of new personnel to be added
	 */
	public void initDatabase(int id) {
		String db = "jdbc:sqlite:C:/sqlite/data";

		try {

			path = DriverManager.getConnection(db);
			Statement command = path.createStatement();
			
			String comm = "DROP TABLE personnel" + id;
			command.execute(comm);
			
			comm = "CREATE TABLE personnel" + id + "(\n" + " Time time PRIMARY KEY,\n" + " bpm integer,\n"
					+ " ppm integer,\n" + " latitude float,\n" + " longitude float\n" + ");";
			command.execute(comm);
			
			System.out.println("Database " + id + " initialized.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void add(int id, java.sql.Time t, int bpm, int ppm, float latitude, float longitude) {
		String db = "jdbc:sqlite:C:/sqlite/data";
		
		String comm = "INSERT INTO personnel" + id + "(time, bpm, ppm, latitude, longitude) VALUES(?,?,?,?,?)";
		
		try{  
			path = DriverManager.getConnection(db); 
            PreparedStatement command = path.prepareStatement(comm);  
            
            command.setTime(1, t);  
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
	
	public void outputTable(int id) {
		String comm = "SELECT * FROM personnel" + id;
		
		try {
			Statement command = path.createStatement();
			ResultSet r = command.executeQuery(comm);
			
			while (r.next()) {  
                System.out.println(r.getTime("time") +  "\t" +   
                                   r.getInt("bpm") + "\t" + 
                                   r.getInt("ppm") + "\t" + 
                                   r.getFloat("latitude") + "\t" + 
                                   r.getFloat("longitude"));  
            }
		} catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }
	}
	
}
