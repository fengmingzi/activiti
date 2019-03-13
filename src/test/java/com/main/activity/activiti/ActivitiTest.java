package com.main.activity.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: fengguang xu
 * @Description: 工作流相关功能测试
 * @Date: 2019/2/28 17:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiTest {

    @Resource
    RuntimeService runtimeService;


    /**
     * 启动流程实例，通过PID
     * 在act_re_procdef数据表中ID_
     */
    @Test
    public void testStartProcessInstanceByPID() {
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceById("shenqingid:1:2504"); //这个就是从部署的时候生成的一个内容
        System.out.println(processInstance.getId());//表act_ru_task中PROC_INST_ID_字段

    }


    /**
     * 根据pdkey启动流程实例，默认启动最高版本的
     * act_re_procdef表中的KEY_
     */
    @Test
    public void testStartPIByPDKEY() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRuntimeService()
                .startProcessInstanceByKey("shenqingid"); //这个字段对应act_re_procdef表中的KEY_
    }



}
