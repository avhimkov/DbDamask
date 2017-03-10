import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        String numberTicket, one, two, three;
        File src, dest;

        /*Дата для генерации резервной копии*/
        Date d = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy-hh.mm");
        String format = format1.format(d);

        /*выбор типа сортировки */
//        System.out.println("Тип БД");
//        BufferedReader inputType = new BufferedReader(new InputStreamReader(System.in));
//        String type = inputType.readLine();

        /*Чтение файла конфигурации*/
        InputStream myFile = new BufferedInputStream(new FileInputStream("config.txt"));
        Scanner myScan = new Scanner(myFile, "windows-1251");
        String line = myScan.nextLine();

        /*Резервное копирование archive.mdb*/
        src = new File(line + "archive.mdb");
        dest = new File(line + "archive" + "-" + format + ".mdb");
        CopyFile.Copy(src, dest);

//        switch (type) {
//            case "access": //for Access
        try {
            /*Чтение базы*/
            String db = "jdbc:ucanaccess://" + line + "archive.mdb"; //D:\1\DamaskLN\

            /*Удаление 0 в номерах тикетов*/
            List<String> list = new ArrayList<>();
            Files.lines(Paths.get("ticket.txt"), StandardCharsets.UTF_8).forEach(list::add);
            for (String name : list) {
                one = name.substring(name.length() - 1);
                two = name.substring(name.length() - 2, name.length() - 1);
                three = name.substring(name.length() - 3, name.length() - 2);

                if (three.contains("0")) {
                    three = "";
                    if (two.contains("0")) {
                        two = "";
                        if (one.contains("0")) {
                            one = "";
                        } else {
                            one = name.substring(name.length() - 1);
                        }
                    } else {
                        two = name.substring(name.length() - 2, name.length() - 1);
                    }
                } else {
                    three = name.substring(name.length() - 3, name.length() - 2);
                }

                /*Генерация номеров тикетов*/
                numberTicket = "'" + name + three + two + one + "'";

                /*Генерация SQL запроса к базе*/
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection conn = DriverManager.getConnection(db);
                Statement st = conn.createStatement();
                st.execute("DELETE FROM Process WHERE Ticket=" + numberTicket);
                st.execute("DELETE FROM Terminal WHERE Ticket=" + numberTicket);
                System.out.println("Delete - - " + numberTicket);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

//            case "mssql": //for MS SQL

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
//                ResultSet rs = stmt.executeQuery("DELETE FROM Process WHERE Ticket=" + numberTicket);
//                ResultSet rs = stmt.executeQuery("DELETE FROM Terminal WHERE Ticket=" + numberTicket);
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
//        }

    }
}
