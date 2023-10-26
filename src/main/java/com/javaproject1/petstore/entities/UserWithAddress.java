package com.javaproject1.petstore.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "userwithaddress", types = { User.class })
public interface UserWithAddress {
    // //closed projection
    // String getFirstName();
    // String getLastName();
    // String getEmail();
    // String getMobile();

    // open projection
    // @Value( "#{target.FirstName}" )
    // String getFname();
    // @Value( "#{target.LastName}" )
    // String getLname();

    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();

    @Value("#{target.userAddress.city}")
    String getCity();

    @Value("#{target.userAddress.state}")
    String getState();

    @Value("#{target.userAddress.country}")
    String getCountry();

    // http://localhost:8080/users/3?projection=userwithaddress url to test in postman
    // we cant get the all the values of city, country and state at a time so we can view it individually

}
