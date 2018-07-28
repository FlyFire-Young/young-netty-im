package org.young.base.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * User: Young
 * Date: 2018/7/9 0009
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class ProxyFactoryBean extends AbstractFactoryBean<Object> implements InvocationHandler {
    private Logger logger = LoggerFactory.getLogger(ProxyFactoryBean.class);

    private Class interfaceClass;

    private SimpleCoseClient simpleCoseClient;

    @Override
    public Class<?> getObjectType() {
        return interfaceClass;
    }

    @Override
    protected Object createInstance() throws Exception {
        logger.info("[代理工厂] 初始化代理Bean : {}", interfaceClass);
        return Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        logger.info("++++++before " + method.getName() + "++++++");
        if ("start".equals(method.getName())) {
            simpleCoseClient.run(0);
            return simpleCoseClient.getChannel();
        }
        return "suc";
    }

    public void setInterfaceClass(Class interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public void setNettyClient(SimpleCoseClient simpleCoseClient) {
        this.simpleCoseClient = simpleCoseClient;
    }
}