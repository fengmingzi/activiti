package com.main.activity.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @Author: fengguang xu
 * @Description: 工作流相关功能测试
 * @Date: 2019/2/28 17:13
 */
public class ActivitiTest {

    @Resource
    RepositoryService repositoryService;

    /**
     * @description 工作流部署
     * @date 2019/2/28 17:16
     * @param
     * @return void
     */
    @Test
    public void creatActivitiTask() {
        //加载前面画的流程图.bpmn和生成的.png
        repositoryService.createDeployment()
                .addClasspathResource("classpath:qingjia.bpmn")
                .addClasspathResource("qingjia.png")
                .deploy();
    }





}
