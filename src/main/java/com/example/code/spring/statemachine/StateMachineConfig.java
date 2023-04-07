package com.example.code.spring.statemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

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
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

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
                .withExternal()
                // 指定状态来源和目标
                .source(States.UNPAID).target(States.WAITING_FOR_RECEIVE)
                // 指定触发事件
                .event(Events.PAY)
                .and()
                .withExternal()
                .source(States.WAITING_FOR_RECEIVE).target(States.DONE)
                .event(Events.RECEIVE);
    }

    /**
     * 为当前的状态机指定了状态监听器
     * listener()调用了下一个内容创建的监听器实例，处理各个发生的状态迁移事件
     */
    @Override
    public void configure(
            StateMachineConfigurationConfigurer<States, Events> config)
            throws Exception {
        config
                .withConfiguration()
                // 指定状态机的处理监听器
                .listener(listener());
    }

    /**
     * 创建StateMachineListener状态监听器的实例，定义具体的状态迁移处理逻辑
     */
    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {

            @Override
            public void transition(Transition<States, Events> transition) {
                if (transition.getTarget().getId() == States.UNPAID) {
                    logger.info("订单创建，待支付");
                    return;
                }
                if (transition.getSource().getId() == States.UNPAID
                        && transition.getTarget().getId() ==
                        States.WAITING_FOR_RECEIVE) {
                    logger.info("用户完成支付，待收货");
                    return;
                }

                if (transition.getSource().getId() ==
                        States.WAITING_FOR_RECEIVE
                        && transition.getTarget().getId() == States.DONE) {
                    logger.info("用户已收货，订单完成");
                }
            }

        };
    }

}
