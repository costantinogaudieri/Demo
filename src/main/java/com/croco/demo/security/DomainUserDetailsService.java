package com.croco.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    /*private final UserRepository userRepository;

    @Autowired
    UserLoginConfigRepository userLoginConfigRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        //Optional<User> userFromDatabase = userRepository.findOneWithAuthoritiesByLogin(lowercaseLogin);

        /*return userFromDatabase.map(user -> {
            if (!user.getActivated()) {
                throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
            }
            if (user.getResetDate() != null) {

                List <UserLoginConfig> ulcList = userLoginConfigRepository.findByCodPar("LOGIN_DURATION");
                if (ulcList != null && ulcList.size() == 1){
                    UserLoginConfig ulc = ulcList.get(0);
                    try {
                        int durationDays = Integer.valueOf(ulc.getDescrizioneLunga());
                        Date resetDate = Date.from(user.getResetDate());
                        Calendar c = Calendar.getInstance();
                        c.setTime(resetDate);
                        c.add(Calendar.DAY_OF_MONTH, durationDays);
                        Date expirationDate = c.getTime();
                        Date today = new Date();
                        if (expirationDate.before(today))
                            throw new UserNotActivatedException("Password exhausted");
                    }catch(Exception e){
                        throw e;
                    }
                }else {
                    throw new UserNotActivatedException("System Login Configuration Error");
                }
            }*/
        List<String> auths = new ArrayList<>();
        //auths.add("ROLE_ADMIN");
        auths.add("ROLE_USER");
            List<GrantedAuthority> grantedAuthorities = auths.stream()
                    .map(authority -> new SimpleGrantedAuthority(authority))
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(lowercaseLogin,
                    //user.getPassword(),
                    "TEST",
                    grantedAuthorities);
        /*}).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the " +
                "database"));*/
    }
}
