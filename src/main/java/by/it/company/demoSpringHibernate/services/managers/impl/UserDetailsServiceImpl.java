package by.it.company.demoSpringHibernate.services.managers.impl;

import by.it.company.demoSpringHibernate.models.UserModel;
import by.it.company.demoSpringHibernate.services.managers.interfaces.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userManager.getUser(username);
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.role));
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getLogin(),
                        user.getPswd(),
                        roles);
        return userDetails;
    }
}
