package com.haisely.community.Service.Impl;

import com.haisely.community.Repository.UserRepository;
import com.haisely.community.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
}
