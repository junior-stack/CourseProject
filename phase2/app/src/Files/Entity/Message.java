package Entity;

public class Message {
    private static int counter = 0;
    private final int messageId;
    private final String senderEmail;
    private final String receiverEmail;
    private final String content;
    private String currentStatus;

    public Message(String senderEmail, String receiverEmail, String content){
        this.messageId = counter;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.content = content;
        this.currentStatus = "unread";
        counter++;
    }

    public int getmessageId(){
        return messageId;
    }

    public String getsenderEmail(){
        return senderEmail;
    }

    public String getreceiverEmail(){
        return receiverEmail;
    }

    public String getContent(){
        return content;
    }

    public String getcurrentStatus(){
        return currentStatus;
    }

    public void setCurrentStatus(String status){
        currentStatus = status;
    }

    @Override
    public String toString() {
        return "Message Id " + messageId + "  " + senderEmail + "sends you a message: " + content + "\n";
    }
}
