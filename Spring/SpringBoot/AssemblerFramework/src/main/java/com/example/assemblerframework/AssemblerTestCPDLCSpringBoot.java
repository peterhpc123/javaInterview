package com.example.assemblerframework;

import com.example.assemblerframework.common.DataFromCMS;
import com.example.assemblerframework.common.DataFromGDS;
import com.example.assemblerframework.extension.AssemblerExtension;
import com.example.assemblerframework.extension.Scenario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//use case
@ExtendWith(AssemblerExtension.class)
@SpringBootTest
public class AssemblerTestCPDLCSpringBoot {
    @Autowired
    DataFromCMS dataFromCMS;
    @Autowired
    DataFromGDS dataFromGDS;
    @Scenario("This is common test for check")
    public void testwithCMSAndGDS() {
        Assertions.assertNotNull(dataFromCMS.getDataFromCMS().get("Prop1"));
        Assertions.assertNotNull(dataFromCMS.getDataFromCMS().get("dep1"));
        Assertions.assertEquals(dataFromGDS.queryfromGDS().get("dep1"), dataFromCMS.getDataFromCMS().get("dep1"));
    }
}
