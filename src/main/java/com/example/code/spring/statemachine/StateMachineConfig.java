package com.example.code.spring.statemachine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * className StateMachineConfig
 * packageName com.example.code.spring.statemachine
 * description
 *
 * @author CCC
 * @date 2021/1/18 23:37
 */
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    /**
     * 初始化当前状态机拥有哪些状态
     */
    @Override
    public void configure(
            StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                // 初始状态 定义了初始状态为待支付UNPAID
                .initial(States.UNPAID)
                // 指定了使用上一步中定义的所有状态作为该状态机的状态定义
                .states(EnumSet.allOf(States.class));
    }

    /**
     * 初始化当前状态机有哪些状态迁移动作
     * 其中迁移动作有来源状态source，目标状态target以及触发事件event
     */
    @Override
    public void configure(
            StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                // 指定状态来源和目标与触发事件
                .withExternal().source(States.UNPAID).target(States.WAITING_FOR_RECEIVE).event(Events.PAY)
                .and()
                .withExternal().source(States.WAITING_FOR_RECEIVE).target(States.DONE).event(Events.RECEIVE);
    }

}
