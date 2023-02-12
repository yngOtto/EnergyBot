package org.example.entitys.response;

public class LoginResponse {

  private String token;
  private ResponseType responseType;

  private String messaget;


  public LoginResponse(String token, ResponseType responseType) {
    this.token = token;
    this.responseType = responseType;
    this.messaget="";
  }

  public String getMessaget() {
    return messaget;
  }

  public void setMessaget(String messaget) {
    this.messaget = messaget;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public ResponseType getResponseType() {
    return responseType;
  }

  public void setResponseType(ResponseType responseType) {
    this.responseType = responseType;
  }
}
