import java.io.*;
import java.sql.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws SQLException {

        try {
            /*чтение файла конфигурации*/
            InputStream myFile = new BufferedInputStream(new FileInputStream("config.txt"));
            Scanner myScan = new Scanner(myFile, "windows-1251");
            String line = myScan.nextLine();

            String db = "jdbc:ucanaccess://"+line+"archive.mdb"; //D:\1\DamaskLN\

            BufferedReader inputTicket = new BufferedReader(new InputStreamReader(System.in));
            String ticket = inputTicket.readLine();
            String text = "'"+ticket+"'";

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(db);
            Statement st = conn.createStatement();
            st.execute("DELETE FROM Process WHERE Ticket="+text);
            st.execute("DELETE FROM Terminal WHERE Ticket="+text);


            System.out.println("Delete");

        } catch (Exception e) {

            System.out.println(e);

        }
    }
}
