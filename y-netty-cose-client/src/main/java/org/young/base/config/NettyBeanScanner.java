package org.young.base.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.young.base.server.ProxyFactoryBean;

import java.util.List;

/**
 * User: Young
 * Date: 2018/7/2 0002
 * Time: 17:32
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class NettyBeanScanner implements BeanFactoryPostProcessor {

    private DefaultListableBeanFactory beanFactory;

    private String basePackage;

    private String clientName;

    public NettyBeanScanner(String basePackage, String clientName) {
        this.basePackage = basePackage;
        this.clientName = clientName;
    }


    /**
     * 注册Bean到Spring的bean工厂
     */
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
        // 加载接口
        List<String> resolverClass = PackageClassUtils.resolver(basePackage);
        for (String clazz : resolverClass) {
            String simpleName;
            if (clazz.lastIndexOf('.') != -1) {
                simpleName = clazz.substring(clazz.lastIndexOf('.') + 1);
            } else {
                simpleName = clazz;
            }
            BeanDefinitionBuilder gd = BeanDefinitionBuilder.genericBeanDefinition(ProxyFactoryBean.class);
            gd.addPropertyValue("interfaceClass", clazz);
            gd.addPropertyReference("nettyClient", clientName);
            this.beanFactory.registerBeanDefinition(simpleName, gd.getRawBeanDefinition());
        }
    }
}
