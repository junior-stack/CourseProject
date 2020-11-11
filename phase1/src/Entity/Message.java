package Entity;

//做message 的人再确认下是否需要这个entity?
//做message 的人可以参照课上twitte 的例子， 很可能能用到observer pattern

public class Message {
    private static int counter=0;
    private String content;
    private int messageID;

    public Message(String content) {
        this.content = content;
        this.messageID = counter;
        counter++;
    }

    public static int getCounter() {return counter;}
    public static void setCounter(int counter) {Message.counter = counter;}
    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}
    public int getMessageID() {return messageID;}
    public void setMessageID(int messageID) {this.messageID = messageID; }
}
