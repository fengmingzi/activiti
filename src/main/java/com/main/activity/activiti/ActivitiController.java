package com.main.activity.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 15227
 * @Description: //TODO
 * @Date: 2019/2/28 17:47
 */
@RestController
@RequestMapping(value = "/act")
public class ActivitiController {

    @PostMapping("/creat")
    public void creatActivitiTask(){
        //加载前面画的流程图.bpmn和生成的.png
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRepositoryService()
                .createDeployment()
                .addClasspathResource("classpath:qingjia.bpmn")
                .addClasspathResource("qingjia.png")
                .deploy();
    }

}
