package com.main.activity;

import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityApplicationTests {

    @Resource
    RepositoryService repositoryService;

    @Test
    public void contextLoads() {
    }

    /**
     * @description 工作流部署
     * @date 2019/2/28 17:16
     * @param
     * @return void
     */
    @Test
    public void creatActivitiTask() {

        /*ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();//用这种方式拿不到ProcessEngine对象
        processEngine.getRepositoryService()
                .createDeployment()
                .addClasspathResource("classpath:qingjia.bpmn")
                .addClasspathResource("qingjia.png")
                .deploy();*/
        //加载前面画的流程图.bpmn和生成的.png
        repositoryService.createDeployment().addClasspathResource("qingjia.bpmn")
                .addClasspathResource("qingjia.png").deploy();
    }


    @Test
    public void deleteActivitiTask(){
        //第一个参数是部署的流程的ID，第二个true表示是进行级联删除
        repositoryService.deleteDeployment("1", true);
    }



}
