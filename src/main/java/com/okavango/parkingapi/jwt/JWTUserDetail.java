package com.okavango.parkingapi.jwt;

import com.okavango.parkingapi.domains.ParkingUser;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JWTUserDetail extends User {

    private ParkingUser parkingUser;

    public JWTUserDetail(ParkingUser parkingUser) {
        super(parkingUser.getUsername(), parkingUser.getPassword(), AuthorityUtils.createAuthorityList(parkingUser.getRole().name()));
        this.parkingUser = parkingUser;
    }

    public Long getId(){
        return parkingUser.getId();
    }

    public String getRole(){
        return parkingUser.getRole().name();
    }
}
