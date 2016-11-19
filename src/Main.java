
import com.healthmarketscience.jackcess.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;

public class Main {

    public static void main(String[] args) throws IOException {
        Database db = DatabaseBuilder.open(new File("archive.mdb"));
        Table table = db.getTable("Process");
        for(Row row : table) {
            System.out.println("Look ma, a row: " + row);
        }
    }
}
