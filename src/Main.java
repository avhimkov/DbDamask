import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.*;
import java.sql.*;


public class Main {
//    static Statement st = null;
//    static ResultSet rs = null;
//    static PreparedStatement pst = null;
//    static Connection connection= null;
    public static void main(String[] args) throws SQLException {
        try {
            BufferedReader inputTicket = new BufferedReader(new InputStreamReader(System.in));
            String ticket = inputTicket.readLine();
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://D:\\1\\DamaskLN\\archive.mdb");
            Statement st = conn.createStatement();
            st.execute("DELETE FROM Process WHERE Ticket="+ticket);
            st.execute("DELETE FROM Terminal WHERE Ticket='20160413-0001010'");
//            String sql = "DELETE FROM Process WHERE Ticket=?"; //20160413-000044
//            String sql = "DELETE FROM Terminal WHERE Ticket=?"; //20160413-000044
//            pst = connection.prepareStatement(sql);
//            pst.setString(1, "'20160413-000066'");
//            pst.execute();
            System.out.println("Delete");

//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                System.out.println("\n" + rs.getString(1));
//            }


//            String dbFileSpec = "C:/Users/Public/example.accdb";
//            try (Database db = DatabaseBuilder.open(new File(dbFileSpec))) {
//                Table tbl = db.getTable("XXXX_XA_Data_1");
//                HashMap rowData = new HashMap();
//                rowData.put("Today's Future Variation Margin", 1);
//                tbl.addRowFromMap(rowData);
//            }
        } catch (Exception e) {

            System.out.println(e);

        }
    }
}
