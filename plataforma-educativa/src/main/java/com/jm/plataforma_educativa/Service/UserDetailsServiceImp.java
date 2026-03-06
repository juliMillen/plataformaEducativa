package com.jm.plataforma_educativa.Service;

import com.jm.plataforma_educativa.DTO.AuthLoginRequestDTO;
import com.jm.plataforma_educativa.DTO.AuthResponseDTO;
import com.jm.plataforma_educativa.Entity.UserSec;
import com.jm.plataforma_educativa.Repository.IUserRepository;
import com.jm.plataforma_educativa.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp  implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSec newUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("The User: "+ username +" was not found"));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        newUser.getRolList().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermissionName())));

        return new User(newUser.getPassword(),
                newUser.getPassword(),
                newUser.isEnable(),
                newUser.isAccountNotExpired(),
                newUser.isAccountNonLocked(),
                newUser.isCredentialsNonExpired(),
                grantedAuthorities);
    }

    public AuthResponseDTO loginUser(AuthLoginRequestDTO authLoginRequest){
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        AuthResponseDTO authResponse= new AuthResponseDTO(username,"Login OK",accessToken,true);
        return authResponse;
    }

    public Authentication authenticate(String username, String password) {

        UserDetails userDetails = this.loadUserByUsername(username);
        if(userDetails == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password.");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }
}
