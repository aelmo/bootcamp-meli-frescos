package com.mercadolibre.bootcampmelifrescos.security;

import com.mercadolibre.bootcampmelifrescos.util.MyUserDetails;

public interface AuthenticationFacade {
    MyUserDetails getUserDetails();
}

