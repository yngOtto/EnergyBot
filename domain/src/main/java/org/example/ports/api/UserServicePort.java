package org.example.ports.api;

import org.example.module.usermodels.MemberDto;

public interface UserServicePort {


  String registrateUser(MemberDto memberDto);

  void  deleteUser(MemberDto memberDto);

  MemberDto login(String email, String password);

  MemberDto verdify(String token);

  MemberDto updateUser(MemberDto memberDto);

  void lockUser(String email);

}
