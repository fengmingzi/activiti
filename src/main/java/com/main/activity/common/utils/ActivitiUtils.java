package com.main.activity.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * @author scw
 * @create 2018-01-24 9:51
 * @desc 针对流程管理的工具类
 **/
@Component("activitiUtis")
@Slf4j
public class ActivitiUtils {
    @Resource(name = "processEngine")
    private ProcessEngine processEngine;

    /**
     * 部署流程
     * @param file 流程的zip文件
     * @param processName  流程的名字
     * @throws IOException
     */
    public void deployeProcess(File file , String processName)throws IOException{
        InputStream inputStream = new FileInputStream(file);
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        this.processEngine.getRepositoryService()
                .createDeployment()
                .name(processName)
                .addZipInputStream(zipInputStream)
                .deploy();
    }

    /**
     * 通过字节流来进行部署流程
     * @param io
     * @param processName
     */
    public  void deplyoyProcessByInputSterm(InputStream io , String processName){
        ZipInputStream zipInputStream = new ZipInputStream(io);
        this.processEngine.getRepositoryService()
                .createDeployment()
                .name(processName)
                .addZipInputStream(zipInputStream)
                .deploy();
    }

    /**
     * 根据模型id部署流程
     * @param modelId 模型id，ACT_RE_MODEL表中的 id 字段
     * @return
     * @throws Exception
     */
    public Deployment deployByModelId(String modelId) throws Exception{
        Model modelData = processEngine.getRepositoryService().getModel(modelId);
        ObjectNode modelNode = (ObjectNode) new ObjectMapper()
                .readTree(processEngine.getRepositoryService().getModelEditorSource(modelData.getId()));

        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

        String processName = modelData.getName() + ".bpmn20.xml";//生成act_re_procdef表中resource_name
        Deployment deployment = processEngine.getRepositoryService().createDeployment().name(modelData.getName())
                .addString(processName, new String(bpmnBytes)).deploy();
        return deployment;

    }


    /**
     * 根据部署名称查询部署信息
     * @param name ACT_RE_DEPLOYMENT表中 NAME_ 字段 或者 ACT_RE_MODEL表中的 NAME_ 字段
     * @return
     */
    public List<Deployment> getDeploymentByName(String name) {
        return this.processEngine.getRepositoryService()
                .createDeploymentQuery().deploymentName(name)
                .orderByDeploymenTime()
                .desc()
                .list();
    }

    /**
     * 查询所有的部署流程
     * @return
     */
    public List<Deployment> getAllDeployment(){
        return this.processEngine.getRepositoryService()
                .createDeploymentQuery()
                .orderByDeploymenTime()
                .desc()
                .list();
    }

    /**
     * 查询所有的部署定义信息
     * @return
     */
    public List<ProcessDefinition> getAllProcessInstance(){
        return this.processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
    }

    /**
     * 根据部署id查询部署定义信息
     * @param deploymentId 部署id，ACT_RE_DEPLOYMENT表的id
     * @return
     */
    public ProcessDefinition getProcessDefinitionByDeploymentId(String deploymentId) {
        return this.processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .deploymentId(deploymentId).singleResult();
    }

    /**
     * 根据部署ID，来删除部署
     * @param deploymenId
     */
    public void deleteDeploymentByPID(String deploymenId){
        this.processEngine.getRepositoryService()
                .deleteDeployment(deploymenId , true);
    }

    /**
     * 查询某个部署流程的流程图
     * @param pid
     * @return
     */
    public InputStream lookProcessPicture(String pid){
        return this.processEngine.getRepositoryService()
                .getProcessDiagram(pid);
    }

    /**
     * 根据流程定义id启动流程实例
     * 并设置businesskey（ACT_RU_EXECUTION表中的business_key）与业务数据进行关联
     * @param procdefId 部署定义id，ACT_RE_PROCDEF表的id
     * @param businessKey 业务key，与ACT_RU_EXECUTION表中的business_key对应，实现与业务数据结合
     * @return
     */
    public ProcessInstance startProcessInstanceById(String procdefId, String businessKey) {
        return this.processEngine.getRuntimeService()
                .startProcessInstanceById(procdefId, businessKey);
    }

    /**
     * 启动流程实例，默认启动最高版本的
     * 通过act_re_procdef表的KEY_
     * @param key  通过act_re_procdef表的KEY_
     * @param businessKey 业务key，自己与实际业务绑定
     */
    public ProcessInstance startProceesInstanceByKey(String key, String businessKey){
        return this.processEngine.getRuntimeService().startProcessInstanceByKey(key, businessKey); //第一个参数，就是流程中自己定义的名字，这个一定要匹配，否则是找不到的。
    }

    /**
     * 查询当前登陆人的所有任务
     * @param userId
     * @return
     */
    public List<Task> queryCurretUserTaskByAssignerr(String userId){
        return this.processEngine.getTaskService()
                .createTaskQuery()
                .taskAssignee(userId)
                .orderByTaskCreateTime()
                .desc()
                .list();
    }

    /**
     * 根据TaskId，获取到当前的执行节点实例对象
     * @param taskId
     * @return
     */
    public ActivityImpl getActivityImplByTaskId(String taskId){
        //首先得到任务
        Task task = this.getTaskByTaskId(taskId);
        //其次，得到流程实例
        ProcessInstance processInstance = this.getProcessInstanceByTask(task);
        //再次，根据流程实例来获取到流程定义
        ProcessDefinitionEntity processDefinitionEntity = this.getProcessDefinitionEntityByTask(task);
        //再根据，流程定义，通过流程实例中来获取到activiti的ID，从而得到acitviImp
        ActivityImpl activity = processDefinitionEntity.findActivity(processInstance.getActivityId());
        return activity;
    }

    /**
     * 根据taskId，判断对应的流程实例是否结束
     * 如果结束了，那么得到的流程实例就是返回一个null
     * 否则就是返回对应的流程实例对象
     * 当然也可以选择返回boolean类型的
     * @param taskId  任务ID
     * @return
     */
    public ProcessInstance isFinishProcessInstancs(String taskId){
        //1,先根据taskid，得到任务
        Task task = getTaskByTaskId(taskId);
        //2:完成当前任务
        finishCurrentTaskByTaskId(taskId);
        //3:得到当前任务对应得的流程实例对象
        ProcessInstance processInstance = getProcessInstanceByTask(task);
        return processInstance;
    }

    /**
     * 获取当前执行节点的所有出口
     * @param activity
     * @return
     */
    public List<PvmTransition> getCurrentActivitiImplPvm(ActivityImpl activity){
        List<PvmTransition> outgoingTransitions = activity.getOutgoingTransitions();
        return outgoingTransitions;
    }

    /**
     * 根据taskId获取到task
     * @param taskId
     * @return
     */
    public Task getTaskByTaskId(String taskId) {
        //得到当前的任务
        Task task = this.processEngine.getTaskService()
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
        return task;
    }

    /**
     * 根据Task中的流程实例的ID，来获取对应的流程实例
     * @param task 流程中的任务
     * @return
     */
    public ProcessInstance getProcessInstanceByTask(Task task) {
        //得到当前任务的流程
        ProcessInstance processInstance = this.processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();
        return processInstance;
    }

    /**
     * 根据Task来获取对应的流程定义信息
     * @param task
     * @return
     */
    public ProcessDefinitionEntity getProcessDefinitionEntityByTask(Task task){
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) this.processEngine.getRepositoryService()
                .getProcessDefinition(task.getProcessDefinitionId());
        return processDefinitionEntity;
    }

    /**
     * 根据taskId获取到businesskey，这个值是管理activiti表和自己流程业务表的关键之处
     * @param taskId 任务的ID
     * @return
     */
    public String getBusinessKeyByTaskId(String taskId){
        Task task = this.getTaskByTaskId(taskId);
        ProcessInstance processInstance = this.getProcessInstanceByTask(task);
        //返回值
        return processInstance.getBusinessKey();
    }

    /**
     * 根据taskId，完成任务
     * @param taskId
     */
    public void finishCurrentTaskByTaskId(String taskId){
        this.processEngine.getTaskService().complete(taskId);
    }

    /**
     * @author fengguang xu
     * @description 根据任务id设置委托人
     * @date 2019/10/10 15:19
     * @param taskId 任务id
     * @param userId 用户id，根据自己业务需求设置
     * @return void
     */
    public void setAssignee(String taskId, String userId){
        this.processEngine.getTaskService().setAssignee(taskId, userId);
    }

    /**
     * 完成任务的同时，进行下一个节点的审批人员的信息的传递
     * @param taskId
     * @param map 下一节点需要设置的信息
     */
    public void finishTaskByTaskId(String taskId , Map map){
        /*Map<String , Object> map = new HashMap<>();
        map.put("assigeUser" , object);*/
        this.processEngine.getTaskService().complete(taskId , map);
    }
}
