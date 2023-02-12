package org.example.adapters;



import org.example.entitys.request.ConfirmRequest;
import org.example.entitys.request.LoginRequest;
import org.example.entitys.request.MemberRequest;
import org.example.entitys.response.LoginResponse;
import org.example.entitys.response.RegistrateResponse;
import org.example.entitys.response.ResponseType;
import org.example.entitys.response.UserConfrimResponse;
import org.example.module.usermodels.MemberDto;
import org.example.ports.api.UserServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping ("/api/v1/user")
public class UserApi {
  @Autowired
  private UserServicePort userServicePort;



  @PostMapping ("/register")
  public RegistrateResponse registrate(@RequestBody MemberRequest memberRequest){
    System.out.println("dskjhjdsngkjdfngkjdfnbvkjdfnvkjdfnb");
    MemberDto memberDto = new MemberDto();
    memberDto.setEmail(memberRequest.getEmail());
    memberDto.setPassword(memberRequest.getPassword());
    RegistrateResponse registrateResponse = new RegistrateResponse();
    registrateResponse.setEmail(memberRequest.getEmail());
    registrateResponse.setToken(this.userServicePort.registrateUser(memberDto));
    registrateResponse.setResponseType(ResponseType.AUTHORIZED);
    return registrateResponse;
  }

  @PostMapping("/confirm")
  public UserConfrimResponse confirm(@RequestBody ConfirmRequest confirmRequest){
    System.out.println(confirmRequest);
    MemberDto memberDto = this.userServicePort.verdify(confirmRequest.getToken());
    System.out.println(confirmRequest.getToken());
    UserConfrimResponse userConfrimResponse = new UserConfrimResponse();
    if(memberDto!=null){
      userConfrimResponse.setConfirmed(true);
      userConfrimResponse.setEmail(memberDto.getEmail());
      userConfrimResponse.setResponseType(ResponseType.AUTHORIZED);
    }else{
      userConfrimResponse.setConfirmed(false);
      userConfrimResponse.setEmail("");
      userConfrimResponse.setResponseType(ResponseType.UNAUTHORIZED);
    }

    return userConfrimResponse;
  }

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest loginRequest){
    System.out.println("Login" + loginRequest.toString());
    LoginResponse loginResponse = new LoginResponse("", ResponseType.UNAUTHORIZED);
    MemberDto memberDto = this.userServicePort.login(loginRequest.getEmail(), loginRequest.getPassword());
    if(memberDto!=null){
      if(Boolean.TRUE.equals(memberDto.getEnabled())){
        loginResponse.setResponseType(ResponseType.AUTHORIZED);
        loginResponse.setToken(memberDto.getAufanticationToken());
      }else{
        loginResponse.setMessaget("{enabled : false}");
      }

    }
    return loginResponse;
  }
}