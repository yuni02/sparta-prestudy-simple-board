package com.sparta.yuni.acceptance.utils;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AcceptanceTestTemplate {

    @Autowired
    private DatabaseCleanup cleanup;
    @Autowired
    private DataLoader dataLoader;

    @BeforeEach
    public void setUp() {
        cleanup.execute();
        dataLoader.loadData();
    }

    protected void cleanUp(){
        cleanup.execute();

    }

    protected String getEmailToken(String email){
        return dataLoader.getEmailToken(email);
    }



}
