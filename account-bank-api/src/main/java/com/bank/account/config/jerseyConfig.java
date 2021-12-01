package com.bank.account.config;

import org.glassfish.jersey.server.ResourceConfig;


public class jerseyConfig extends ResourceConfig {
    public void JerseyConfig() {
       // register(ClientControler.class);
        //register(AccountControler.class);
        packages("com.bank.account");

    }

}
