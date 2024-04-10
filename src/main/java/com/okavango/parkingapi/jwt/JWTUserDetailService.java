package com.okavango.parkingapi.jwt;


import com.okavango.parkingapi.domains.ParkingUser;
import com.okavango.parkingapi.repositories.ParkingUserRepository;
import com.okavango.parkingapi.services.ParkingUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JWTUserDetailService implements UserDetailsService {

    private final ParkingUserService parkingUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ParkingUser parkingUser = parkingUserService.findUsername(username);
        return new JWTUserDetail(parkingUser);
    }

    public JWToken getTokenAuthenticated(String username){
        ParkingUser.Role role = parkingUserService.roleByUsername(username);
        return  JWTUtils.createToken(username, role.name().substring("ROLE_".length()));
    }
}
