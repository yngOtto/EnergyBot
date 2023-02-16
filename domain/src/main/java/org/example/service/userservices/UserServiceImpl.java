package org.example.service.userservices;

import org.example.module.usermodels.MemberDto;
import org.example.ports.api.UserServicePort;
import org.example.ports.spi.MailPersistencePort;
import org.example.ports.spi.MemberPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserServicePort {

  private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  private MemberPersistencePort memberPersistencePort;

  private MailPersistencePort mailPersistencePort;

  public UserServiceImpl(MemberPersistencePort memberPersistencePort, MailPersistencePort mailPersistencePort) {
    this.memberPersistencePort = memberPersistencePort;
    this.mailPersistencePort = mailPersistencePort;
  }

  @Override
  public String registrateUser(MemberDto memberDto) {
    String token = this.memberPersistencePort.register(memberDto);
    this.mailPersistencePort.sendEmail(memberDto.getEmail(), "Add user", "The userToken : "+token +"     Please enter this in the usertoken boks");
    return token;
  }

  @Override
  public void deleteUser(MemberDto memberDto) {
    this.memberPersistencePort.deleteMember(memberDto);

  }

  @Override
  public MemberDto login(String email, String password) {
    return this.memberPersistencePort.login(email, password);
  }

  @Override
  public MemberDto verdify(String token) {
    MemberDto memberDto = this.memberPersistencePort.confirmToken(token);
    if(memberDto!=null){
      memberDto.setEnabled(true);
      memberDto.setLocked(false);
      MemberDto newMemberDto = this.memberPersistencePort.updateUser(memberDto);
      this.mailPersistencePort.sendEmail(newMemberDto.getEmail(), "Hej "+newMemberDto.getEmail()+" Acount Verdified "," the Acount is verdified and enabled");
      return newMemberDto;
    }
    return null;
  }
  @Override
  public MemberDto updateUser(MemberDto memberDto) {
    return this.memberPersistencePort.updateUser(memberDto);
  }

  @Override
  public void lockUser(String email) {
    this.memberPersistencePort.lockUser(email);
  }


}
