package com.group0014.iconference.Model;

public class User {

  private String uid;
  private String name;
  private String phone;
  private String email;
  private String type;

  public User(String uid, String name, String phone, String email, String type) {
    this.uid = uid;
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.type = type;
  }

  public User() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String id) {
    this.uid = uid;
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
