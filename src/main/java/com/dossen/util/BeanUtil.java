package com.dossen.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @项目名称：dossen-api-service
 * @类名称：BeanUtil
 * @类描述：获取实例
 * @创建人：万星明
 * @创建时间：2020/7/27
 */

@Component
public class BeanUtil implements ApplicationContextAware {

    /**
     * 将管理上下文的applicationContext设置成静态变量，供全局调用
     */
    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("管理上下文ApplicationContext加载完毕...");
    }

    /**
     * 定义一个获取已经实例化bean的方法
     * @param tClass 类
     * @param <T> 泛型
     * @return 类实例
     */
    public static <T> T getBean(Class<T> tClass){
        if(null==applicationContext){
            return null;
        }
        return applicationContext.getBean(tClass);
    }

}
