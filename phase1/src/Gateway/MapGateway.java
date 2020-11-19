package Gateway;

import java.util.HashMap;
import java.util.Map;

/**
 * An interface to write Map into file in database and read from file to return HashMap objects.
 * @author Jun Xing
 * @version 1.0
 */
public interface MapGateway {

    public void write(Map map);

    public HashMap read();
}
