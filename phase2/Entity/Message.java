package Entity;

/**
 * The class representing the message.
 *
 * @author Zhongyuan Liang & Jiahao Zhang
 */
public class Message {
    private static int counter = 0;
    private final int messageId;
    private final String senderEmail;
    private final String receiverEmail;
    private final String content;
    private String currentStatus;

    /**
     * This is the constructor of the message.
     * @param senderEmail The email address of the sender.
     * @param receiverEmail The email address of the receiver.
     * @param content The content of the message.
     */
    public Message(String senderEmail, String receiverEmail, String content){
        this.messageId = counter;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.content = content;
        this.currentStatus = "unread";
        counter++;
    }

    /**
     * This method is the getter for the id of this message.
     * @return int The id of this message.
     */
    public int getmessageId(){
        return messageId;
    }

    /**
     * This method is the getter for the sender email address.
     * @return String The email address of the sender.
     */
    public String getsenderEmail(){
        return senderEmail;
    }

    /**
     * This method is the getter for the receiver email address.
     * @return String The email address of the receiver.
     */
    public String getreceiverEmail(){
        return receiverEmail;
    }

    /**
     * This method is the getter for the content of the message.
     * @return String The content of the message.
     */
    public String getContent(){
        return content;
    }

    /**
     * This method is the getter for the current status of the message.
     * @return String The current status of the message.
     */
    public String getcurrentStatus(){
        return currentStatus;
    }

    /**
     * This method is the setter for the current status of the message.
     * @param status The new status for the current message.
     */
    public void setCurrentStatus(String status){
        currentStatus = status;
    }

    @Override
    public String toString() {
        return "Message Id " + messageId + "  " + senderEmail + "sends you a message: " + content + "\n";
    }
}
