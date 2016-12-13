package by.mnk.htp.glotovs.msr.services.impl;

import by.mnk.htp.glotovs.msr.entities.UserEntity;
import by.mnk.htp.glotovs.msr.enums.Access;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;
import by.mnk.htp.glotovs.msr.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("authenticationService")
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String phoneNumber)
            throws UsernameNotFoundException {
        UserEntity userEntity = null;
        try {
            userEntity = userService.getUserEntityByPhone(phoneNumber);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (userEntity == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(userEntity.getPhone(), userEntity.getPassword(),
                Access.ACTIVE.getState().equals(userEntity.getAccess()), true, true, true, getGrantedAuthorities(userEntity));
    }


    private List<GrantedAuthority> getGrantedAuthorities(UserEntity userEntity) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getType()));
        return authorities;
    }
}
