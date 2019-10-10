package com.main.activity.activiti;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.main.activity.common.Utils.ActivitiUtils;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: fengguang xu
 * @Description: 创建工作流模型
 * @Date: 2019/2/28 17:47
 */
@Slf4j
@RestController
@RequestMapping(value = "/act")
public class ActivitiController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ActivitiUtils activitiUtils;


    /**
     * 创建模型
     */
    @RequestMapping("/create")
    public void create(HttpServletRequest request, HttpServletResponse response) {
        try {
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

            RepositoryService repositoryService = processEngine.getRepositoryService();

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, "nametest");
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            String description = "工作流测试";
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName("modelname");
            modelData.setKey("12313123");

            //保存模型
            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
        } catch (Exception e) {
            System.out.println("创建模型失败：");
        }
    }

    /**
     * 部署模型.
     *  act_re_deployment表中增加一条数据
     *  act_re_procdef表中增加一条数据，生成.xml和.png
     * @param modelId 模型id（act_re_model的id）
     */
    @PostMapping("/model/deploy")
    public Map<String, Object> deployModel(String modelId){
        Map<String, Object> result = new HashMap<>();
        try {
            activitiUtils.deployByModelId(modelId);
            result.put("Message", "部署成功");
            return result;
        } catch (Exception e) {
            log.error("modelId："+modelId+",部署流程异常：", e);
            result.put("Message", "部署失败");
            return result;
        }
    }

    /**
     * 查询所有部署的流程
     * @return
     */
    @GetMapping("/getAllDeplyoment")
    public List<Map<String, String>> getAllDeplyoment() {
        List<Deployment> deployments = activitiUtils.getAllDeployment();
        List<Map<String, String>> list = new ArrayList<>();
        for (Deployment deployment : deployments) {
            System.out.println("部署的名称："+deployment.getName()+"部署id："+deployment.getId());
            Map<String, String> map = new HashMap<>();
            map.put("name", deployment.getName());
            map.put("id", deployment.getId());
            list.add(map);
        }
        return list;
    }

    /**
     * 启动流程实例
     * 在实际业务中可能在创建业务数据时选择部署的流程后，直接将业务数据id作为businessKey实现关联
     * @param deploymentId
     * @param businessKey
     * @return
     */
    public Map<String, String> startProceesInstance(String deploymentId, String businessKey) {
        Map<String, String> map = new HashMap<>();
        //根据部署id查询出部署定义信息
        ProcessDefinition processDefinition = activitiUtils.getProcessDefinitionByDeploymentId(deploymentId);
        String prodefId = processDefinition.getId();
        //根据部署定义id来启动流程实例，同时将要关联的业务与ACT_RU_EXECUTION表中的business_key字段做关联
        ProcessInstance processInstance = activitiUtils.startProcessInstanceById(prodefId, businessKey);
        map.put("processInstance", processInstance.getId());
        map.put("Message", "流程实例启动成功");
        return map;
    }


}
