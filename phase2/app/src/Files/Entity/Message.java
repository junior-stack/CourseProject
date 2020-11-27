package Entity;

public class Message {
    private static int counter = 0;
    private int messageId;
    private String senderEmail;
    private String receiverEmail;
    private String content;
    private String currentStatus;

    public Message(String senderEmail, String receiverEmail, String content){
        this.messageId = counter;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.content = content;
        this.currentStatus = "unread";
        counter++;
    }

}
