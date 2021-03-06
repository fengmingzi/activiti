package com.main.activity;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

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

    /**
     * @description 删除工作流
     * @date 2019/3/1 17:18
     * @param
     * @return void
     */
    @Test
    public void deleteActivitiTask(){
        //第一个参数是部署的流程的ID，第二个true表示是进行级联删除
        repositoryService.deleteDeployment("1", true);
    }

    /**
     * @description 查询流程信息（act_re_deployment）
     * @date 2019/3/1 17:36
     * @param
     * @return void
     */
    @Test
    public void queryDeploymentByName(){
        List<Deployment> deployments = repositoryService.createDeploymentQuery().orderByDeploymenTime().desc().deploymentId("2501").list();
        for (Deployment deployment : deployments) {
            System.out.println("流程id："+deployment.getId());
        }
    }

    /**
     * @description 查询流程定义（act_re_procdef）
     *              在创建流程时整个流程定义的id（key）和name等
     * @date 2019/3/1 17:42
     * @param
     * @return void
     */
    @Test
    public void testQueryAllPD() {
        List<ProcessDefinition> pdList = repositoryService
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
        for (ProcessDefinition pd : pdList) {
            System.out.println(pd.getName());
            System.out.println(pd.getId());
        }
    }

    /**
     * 查看流程图
     * 根据deploymentId和name(在act_ge_bytearray数据表中)
     */
    @Test
    public void testShowImage() throws Exception {
        InputStream inputStream = repositoryService
                /**
                 * deploymentID
                 * 文件的名称和路径
                 */
                .getResourceAsStream("2501", "qingjia.png");
        OutputStream outputStream3 = new FileOutputStream("e:/processimg.png");
        int b = -1;
        while ((b = inputStream.read()) != -1) {
            outputStream3.write(b);
        }
        inputStream.close();
        outputStream3.close();
    }

    /**
     * 根据pdid（ID_字段）查看图片(在act_re_procdef数据表中)
     * @throws Exception
     */
    @Test
    public void testShowImage2() throws Exception {
        InputStream inputStream = repositoryService
                .getProcessDiagram("shenqingid:1:2504");
        OutputStream outputStream = new FileOutputStream("e:/processimg.png");
        int b = -1;
        while ((b = inputStream.read()) != -1) {
            outputStream.write(b);
        }
        inputStream.close();
        outputStream.close();

    }

    /**
     * 查看bpmn文件(在act_re_procdef数据表中)
     */
    @Test
    public void testShowBpmn() throws Exception {
        InputStream inputStream = repositoryService
                .getProcessModel("shenqingid:1:2504");
        OutputStream outputStream = new FileOutputStream("e:/processimg.bpmn");
        int b = -1;
        while ((b = inputStream.read()) != -1) {
            outputStream.write(b);
        }
        inputStream.close();
        outputStream.close();
    }






}
