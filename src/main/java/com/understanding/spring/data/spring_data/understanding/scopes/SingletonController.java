package com.understanding.spring.data.spring_data.understanding.scopes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("singleton")
public class SingletonController {
    @Autowired
    PrototypeUser prototypeUser;

    @Autowired
    SingletonUser singletonUser;
}
