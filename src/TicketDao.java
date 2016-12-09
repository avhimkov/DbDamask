import com.agileasoft.jackcess.orm.dao.AccessDao;

import java.io.File;
import java.io.IOException;

public class TicketDao extends AccessDao<Ticket> {
    public TicketDao(File accessFile) throws IOException {
        super(accessFile, Ticket.class);
    }
}
