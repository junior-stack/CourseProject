package exception;

/**
 * Created by yezhou on 2020/11/12
 **/
public class InvertedTime extends Exception{
    public InvertedTime(){super();}

    @Override
    public String getMessage() {
        return "The time you enter should be inverted";
    }
}
