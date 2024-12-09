package com.understanding.spring.data.spring_data.understanding.scopes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeController {

    @Autowired
    PrototypeUser prototypeUser;

    @Autowired
    SingletonUser singletonUser;
}
