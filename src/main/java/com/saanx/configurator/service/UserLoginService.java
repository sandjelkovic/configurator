package com.saanx.configurator.service;

import com.saanx.configurator.data.entity.User;
import com.saanx.configurator.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserLoginService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findOneByUsername(username)
//				.orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " doesn't exist."));
//		return mapUserToSpringUserDetails(user);

		return userRepository.findOneByUsername(username)
				.map(this::mapUserToSpringUserDetails)
				.orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " doesn't exist."));
	}

	private UserDetails mapUserToSpringUserDetails(User user) {
		List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
				.map(SimpleGrantedAuthority::new)
				.collect(toList());
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(),
				accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
	}
}
