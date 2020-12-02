package com.group0014.iconference;

public class User {

  private String id;
  private String name;
  private String phone;
  private String type;

  public User(String id, String name, String phone, String type) {
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.type = type;
  }

  public User() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
