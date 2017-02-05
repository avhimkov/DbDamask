import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        /*выбор типа сортировки*/
        System.out.println("Тип БД");
        BufferedReader inputType = new BufferedReader(new InputStreamReader(System.in));
        String type = inputType.readLine();

        switch (type) {
            case "access": //for Access
                try {
                    /*чтение файла конфигурации*/
                    InputStream myFile = new BufferedInputStream(new FileInputStream("config.txt"));
                    Scanner myScan = new Scanner(myFile, "windows-1251");
                    String line = myScan.nextLine();

                    String db = "jdbc:ucanaccess://" + line + "archive.mdb"; //D:\1\DamaskLN\

                    List<String> list = new ArrayList<>();
                    Files.lines(Paths.get("ticket.txt"), StandardCharsets.UTF_8).forEach(list::add);
                    for (String name : list) {
                        //no tested
                        String end = name.substring(name.length()-2, name.length()-1);

                        String text = "'" + name + end +"'";
                        System.out.println(name);

                        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                        Connection conn = DriverManager.getConnection(db);
                        Statement st = conn.createStatement();
                        st.execute("DELETE FROM Process WHERE Ticket=" + text);
                        st.execute("DELETE FROM Terminal WHERE Ticket=" + text);

                        System.out.println("Delete");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            case "mssql": //for MS SQL

//                try {
//                    /*чтение файла конфигурации*/
//                    InputStream myFile = new BufferedInputStream(new FileInputStream("config.txt"));
//                    Scanner myScan = new Scanner(myFile, "windows-1251");
//                    String line = myScan.nextLine();
//
//                    String db = "jdbc:sqlserver://localhost:4623;" + "databaseName=kherson;user=testing;password=J5O6QF0A;";
//
//                    Connection con = DriverManager.getConnection(connectionUrl);
//                Statement stmt = con.createStatement();
//
//                //query to be run against pkms
//                ResultSet rs = stmt.executeQuery("SELECT * FROM ds_orders");
//
//                while (rs.next()) {
//                    String str = rs.getString(1)+ "  " + rs.getString(2) + "  " + rs.getString(3);
//                    System.out.println(str);
//                }
//                //Create table for query results to be displayed
//                //JTable table = new JTable(rs);
//                //  table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//                // JScrollPane resultsPane = new JScrollPane(table);
//                //  contentPane.add(resultsPane, BorderLayout.CENTER);
//                rs.close();
//                stmt.close();
//                con.close();
//                pack();
//                setVisible(true);
//                    }
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
        }

    }
}
