package com.carrot.jedis;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: Created by carrot
 * 2020/12/13 14:40
 */
public class SayHelloEvent extends ApplicationEvent {

    private String name;

    private Integer age;

    public SayHelloEvent(Object source, String name, Integer age) {
        super(source);
        this.name = name;
        this.age = age;
    }

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public SayHelloEvent(Object source) {
        super(source);
    }


    @Override
    public String toString() {
        return "SayHelloEvent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
