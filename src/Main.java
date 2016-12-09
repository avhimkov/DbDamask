import com.agileasoft.jackcess.orm.dao.AccessDao;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;


public class Main {
//    static Statement st = null;
//    static ResultSet rs = null;
//    static PreparedStatement pst = null;
//    static Connection connection= null;
    public static void main(String[] args) throws SQLException {

        AccessDao<Ticket> clientDao = new new TicketDao(new File("/path/to/your/mdb/file.mdb"));
        List<Ticket> listClients = clientDao.findAll();
        for(Ticket client : listClients) {
            System.out.println(client.toString());
        }
        try {
            /*чтение файла конфигурации*/
            InputStream myFile = new BufferedInputStream(new FileInputStream("config.txt"));
            Scanner myScan = new Scanner(myFile, "windows-1251");
            String line = myScan.nextLine();

            String db = "jdbc:ucanaccess://"+line+"archive.mdb"; //D:\1\DamaskLN\

            BufferedReader inputTicket = new BufferedReader(new InputStreamReader(System.in));
            String ticket = inputTicket.readLine();

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(db);
            Statement st = conn.createStatement();
            st.execute("DELETE FROM Process WHERE Ticket='20160413-0001010'");
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
