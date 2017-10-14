package com.amass.credit.modules.quartz.jobs;

import java.lang.reflect.Method;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/** 
 * @ClassName CommJobBean 
 * @Description 公用任务
 * @version 1.0
 */
public class CommJobBean extends QuartzJobBean { 

    private String targetObject; 
    private String targetMethod; 
    private ApplicationContext ctx; 

    @Override  
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {  
    
        try {
            Object otargetObject = ctx.getBean(targetObject); 
            Method m = null; 
        
                try { 
                    m = otargetObject.getClass().getMethod(targetMethod); 
                    m.invoke(otargetObject); 
                } catch (SecurityException e) { 
                    e.printStackTrace(); 
                } catch (NoSuchMethodException e) { 
                    e.printStackTrace(); 
                } 
        } catch (Exception e) { 
            throw new JobExecutionException(e); 
        } 
    
    } 
    
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.ctx = applicationContext; 
    } 
    
    public void setTargetObject(String targetObject) { 
        this.targetObject = targetObject; 
    } 
    
    public void setTargetMethod(String targetMethod) {  
        this.targetMethod = targetMethod;  
    }  

}