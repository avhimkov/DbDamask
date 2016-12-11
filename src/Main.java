import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) throws SQLException {

        try {
            /*чтение файла конфигурации*/
            InputStream myFile = new BufferedInputStream(new FileInputStream("config.txt"));
            Scanner myScan = new Scanner(myFile, "windows-1251");
            String line = myScan.nextLine();

            String db = "jdbc:ucanaccess://"+line+"archive.mdb"; //D:\1\DamaskLN\

//            BufferedReader inputTicket = new BufferedReader(new InputStreamReader(System.in));
//            String ticket = inputTicket.readLine();
            List<String> list = new ArrayList<>();
            Files.lines(Paths.get("ticket.txt"), StandardCharsets.UTF_8).forEach(list::add);
            //подстановка кавычек
            String text = "'"+list+"'";
            System.out.println(list);

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
