package com.carrot.jedis.listener;

import com.carrot.jedis.SayHelloEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

/**
 * @Author: Created by carrot
 * 2020/12/13 14:39
 */
@Slf4j
//@Component
public class AnnotationEventListener {

    @EventListener
    public void say(SayHelloEvent event){
        log.info("触发事件:{}",event);
    }

    @EventListener
    public void contextRefresh(ContextRefreshedEvent event){
        log.info("触发事件:{}",ContextRefreshedEvent.class);
    }

    @EventListener
    public void contextStarted(ContextStartedEvent event){
        log.info("触发事件:{}",ContextStartedEvent.class);
    }

    @EventListener
    public void contextStopped(ContextStoppedEvent event){
        log.info("触发事件:{}",ContextStoppedEvent.class);
    }

    @EventListener
    public void contextClosed(ContextClosedEvent event){
        log.info("触发事件:{}",ContextClosedEvent.class);
    }

    @EventListener
    public void event(ApplicationEvent event){
        log.info("触发事件:{}",event.getClass());
    }

}
