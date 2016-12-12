import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        try {
            /*чтение файла конфигурации*/
            InputStream myFile = new BufferedInputStream(new FileInputStream("config.txt"));
            Scanner myScan = new Scanner(myFile, "windows-1251");
            String line = myScan.nextLine();

            String db = "jdbc:ucanaccess://"+line+"archive.mdb"; //D:\1\DamaskLN\

            List<String> list = new ArrayList<>();
            Files.lines(Paths.get("ticket.txt"), StandardCharsets.UTF_8).forEach(list::add);
            for (String name : list){
                String text = "'"+name+"'";
                System.out.println(name);

                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection conn = DriverManager.getConnection(db);
                Statement st = conn.createStatement();
                st.execute("DELETE FROM Process WHERE Ticket="+text);
                st.execute("DELETE FROM Terminal WHERE Ticket="+text);

                System.out.println("Delete");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
