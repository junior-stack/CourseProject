package Gateway;

import java.util.HashMap;
import java.util.Map;

public interface MapGateway {

    public void write(Map map);

    public HashMap read();
}
