import java.lang.*;
import java.sql.*;


public class Main {

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://D:\\1\\DamaskLN\\archive.accdb");

            Statement st = conn.createStatement();

            String sql = "Select * from Process";

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                System.out.println("\n" + rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
            }

        } catch (Exception e) {

            System.out.println(e);

        }
    }
}
