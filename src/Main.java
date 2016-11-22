import java.lang.*;
import java.sql.*;


public class Main {

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://D:\\1\\DamaskLN\\archive.mdb");

            Statement st = conn.createStatement();

            String sql = "DELETE FROM Process WHERE Ticket='20160413-000077'";
            st.execute(sql);

//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                System.out.println("\n" + rs.getString(1));
//            }

        } catch (Exception e) {

            System.out.println(e);

        }
    }
}
