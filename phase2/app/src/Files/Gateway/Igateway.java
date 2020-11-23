package Gateway;

import java.util.ArrayList;
import java.util.List;

/**
 * An interface to write List into file in database and read from file to return ArrayList objects.
 *
 * @author Jun Xing
 * @version 1.0
 */
public interface Igateway {

  public void write(List list);

  public ArrayList read();

}
