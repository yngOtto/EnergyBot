package org.example.adapters;

import org.example.entity.Member;
import org.example.module.usermodels.MemberDto;
import org.example.ports.spi.MemberPersistencePort;
import org.example.repositorys.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberJpaAdapter implements MemberPersistencePort {

  @Autowired
  private MemberRepository memberRepository;
  @Override
  public String register(MemberDto memberDto) {
    Member member = new Member();
    member.setEmail(memberDto.getEmail());
    member.setPassword(memberDto.getPassword());
    member.setPassword(memberDto.getPassword());
    member.setToken(UUID.randomUUID().toString());
    Member newMember = this.memberRepository.save(member);
    return newMember.getToken();
  }

  @Override
  public MemberDto confirmToken(String token) {
    Optional<Member> member = this.memberRepository.findAllByToken(token);
    if(member.isPresent()){
      MemberDto memberDto = new MemberDto();
      memberDto.setEmail(member.get().getEmail());
      memberDto.setId(member.get().getId());
      memberDto.setEnabled(member.get().getEnabled());
      memberDto.setLocked(member.get().getLocked());
      memberDto.setToken(member.get().getToken());
      return memberDto;
    }

    return null;
  }

  @Override
  public void deleteMember(MemberDto memberDto) {
    this.memberRepository.deleteAllByEmail(memberDto.getEmail());
  }

  @Override
  public MemberDto login(String email, String password) {
    MemberDto memberDto = new MemberDto();
    Optional<Member> member =this.memberRepository.findAllByEmailAndPassword(email,password);
    if(member.isPresent()){
      Member m = member.get();
      String authicanitcationToken = UUID.randomUUID().toString();
      m.setAufanticationToken(authicanitcationToken);
      Member newmember =this.memberRepository.save(m);
      memberDto.setEmail(newmember.getEmail());
      memberDto.setId(newmember.getId());
      memberDto.setEnabled(newmember.getEnabled());
      memberDto.setLocked(newmember.getLocked());
      memberDto.setAufanticationToken(newmember.getAufanticationToken());
      return memberDto;
    }
    return null;
  }

  @Override
  public MemberDto updateUser(MemberDto memberDto) {
    Optional<Member> member = this.memberRepository.findById(memberDto.getId()).stream().findFirst();
    if(member.isPresent()){
      Member m = member.get();
      m.setToken(memberDto.getToken());
      m.setEnabled(memberDto.getEnabled());
      m.setEmail(memberDto.getEmail());
      m.setLocked(memberDto.getLocked());
      Member newmember =this.memberRepository.save(m);
      MemberDto newmemberDto = new MemberDto();
      newmemberDto.setEmail(newmember.getEmail());
      newmemberDto.setId(newmember.getId());
      newmemberDto.setEnabled(newmember.getEnabled());
      newmemberDto.setLocked(newmember.getLocked());
      newmemberDto.setToken(newmember.getToken());
      return newmemberDto;

    }
    return null;
  }

  @Override
  public void lockUser(String email) {
    Optional<Member> members = this.memberRepository.findMembersByEmail(email);
    if(members.isPresent()){
      members.get().setLocked(true);
    }
  }

  @Override
  public List<MemberDto> getAllMembers() {
    List<Member> membersList = this.memberRepository.findAll();
    List<MemberDto> memberDtoList = new ArrayList<>();
    for (Member member:membersList) {
      if (member.getEnabled()){
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail(member.getEmail());
        memberDtoList.add(memberDto);
      }
    }

    return memberDtoList;
  }
}
