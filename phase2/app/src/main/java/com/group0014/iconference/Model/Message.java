package com.group0014.iconference.Model;

import org.jetbrains.annotations.NotNull;

public class Message {

  String message;
  String name;
  String key;

  public Message(String message, String name) {
    this.message = message;
    this.name = name;
  }

  public Message() {
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  @NotNull
  @Override
  public String toString() {
    return "Message{" +
        "message='" + message + '\'' +
        ", name='" + name + '\'' +
        ", key='" + key + '\'' +
        '}';
  }
}
