package com.main.activity.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * 采购审批流程中，价格判断连线监听器，为了实现给下一审核节点设置审核人
 */
public class priceListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("进入监听器");
        String eventName = delegateExecution.getEventName();
        System.out.println("evenName:"+eventName);
    }
}
