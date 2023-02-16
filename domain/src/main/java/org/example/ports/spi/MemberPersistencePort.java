package org.example.ports.spi;

import org.example.module.usermodels.MemberDto;

import java.util.List;

public interface MemberPersistencePort {

  String register(MemberDto memberDto);

  MemberDto confirmToken(String token);

  void deleteMember(MemberDto memberDto);

  MemberDto login(String email, String password);

  MemberDto updateUser(MemberDto memberDto);

  void lockUser(String email);

  List<MemberDto> getAllMembers();

}
