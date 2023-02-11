package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
  @Id
  @Column (name = "id", nullable = false)
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String password;
  private Boolean locked = false;
  private Boolean enabled = false;
  private String token;

  private String aufanticationToken;

  public String getAufanticationToken() {
    return aufanticationToken;
  }

  public void setAufanticationToken(String aufanticationToken) {
    this.aufanticationToken = aufanticationToken;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getLocked() {
    return locked;
  }

  public void setLocked(Boolean locked) {
    this.locked = locked;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
