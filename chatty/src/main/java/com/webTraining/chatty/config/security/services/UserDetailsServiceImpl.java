package com.webTraining.chatty.config.security.services ;

import com.webTraining.chatty.models.Users;
import com.webTraining.chatty.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UsersRepository usersRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = usersRepository.findByUsername(username);
    if (! user.isPresent()) throw new RuntimeException("No such user exists");
    return UserDetailsImpl.build(user.get());

  }

}
