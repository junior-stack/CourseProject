package Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The class representing the message.
 *
 * @author Zhongyuan Liang & Jiahao Zhang
 */
@DatabaseTable(tableName = "message")
public class Message {

  @DatabaseField(columnName = "message_id", generatedId = true)
  private int messageId;
  @DatabaseField(columnName = "sender_email")
  private String senderEmail;
  @DatabaseField(columnName = "receiver_email")
  private String receiverEmail;
  @DatabaseField(columnName = "content")
  private String content;
  @DatabaseField(columnName = "current_status")
  private String currentStatus;

  /**
   * This is the constructor of the message.
   *
   * @param senderEmail   The email address of the sender.
   * @param receiverEmail The email address of the receiver.
   * @param content       The content of the message.
   */
  public Message(String senderEmail, String receiverEmail, String content) {

    this.senderEmail = senderEmail;
    this.receiverEmail = receiverEmail;
    this.content = content;
    this.currentStatus = "unread";
  }

  public Message() {
  }

  /**
   * This method is the getter for the id of this message.
   *
   * @return int The id of this message.
   */
  public int getmessageId() {
    return messageId;
  }

  /**
   * This method is the getter for the sender email address.
   *
   * @return String The email address of the sender.
   */
  public String getsenderEmail() {
    return senderEmail;
  }

  /**
   * This method is the getter for the receiver email address.
   *
   * @return String The email address of the receiver.
   */
  public String getreceiverEmail() {
    return receiverEmail;
  }

  /**
   * This method is the getter for the content of the message.
   *
   * @return String The content of the message.
   */
  public String getContent() {
    return content;
  }

  /**
   * This method is the getter for the current status of the message.
   *
   * @return String The current status of the message.
   */
  public String getcurrentStatus() {
    return currentStatus;
  }

  /**
   * This method is the setter for the current status of the message.
   *
   * @param status The new status for the current message.
   */
  public void setCurrentStatus(String status) {
    currentStatus = status;
  }

  @Override
  public String toString() {
    return "Id: " + messageId + " \n"
        + "From: "+senderEmail + " \n"
        + "Content: " + content;
  }
}
