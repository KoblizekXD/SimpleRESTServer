package com.koblizek.server.util.impl;

import com.koblizek.server.RESTServer;
import com.koblizek.server.request.Request;
import com.koblizek.server.util.Component;
import com.koblizek.server.util.RESTServerException;
import com.koblizek.server.util.annotations.Delete;
import com.koblizek.server.util.annotations.Get;
import com.koblizek.server.util.annotations.Post;
import com.koblizek.server.util.annotations.Put;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RESTServerImpl extends RESTServer {
    private final List<Method> components;

    public RESTServerImpl() {
        components = new ArrayList<>();
    }
    @Override
    public RESTServer registerComponent(Class<? extends Component> componentClass) {
        for (Method declaredMethod : componentClass.getDeclaredMethods()) {
            if (
                    componentClass.isAnnotationPresent(Get.class) ||
                    componentClass.isAnnotationPresent(Post.class) ||
                    componentClass.isAnnotationPresent(Put.class) ||
                    componentClass.isAnnotationPresent(Delete.class) &&
                            declaredMethod.getParameterCount() == 1 &&
                            declaredMethod.getParameters()[0].getType() == Request.class
            ) {
                components.add(declaredMethod);
            } else {
                throw new RESTServerException("Component " + declaredMethod.getName()
                        + "couldn't be registered because one of conditions was not met");
            }
        }
        return this;
    }

    @Override
    public void publish(int port) {

    }

    @Override
    public void stop() {

    }
}
