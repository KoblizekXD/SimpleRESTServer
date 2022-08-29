package com.koblizek.server.util.impl;

import com.koblizek.server.FunctionalComponent;
import com.koblizek.server.RESTServer;
import com.koblizek.server.request.Request;
import com.koblizek.server.util.Component;
import com.koblizek.server.util.RESTServerException;
import com.koblizek.server.util.annotations.Delete;
import com.koblizek.server.util.annotations.Get;
import com.koblizek.server.util.annotations.Post;
import com.koblizek.server.util.annotations.Put;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class RESTServerImpl extends RESTServer {
    private final List<FunctionalComponent> components;
    private final HttpServer server;

    public RESTServerImpl() throws IOException {
        components = new ArrayList<>();
        server = HttpServer.create();
    }
    @Override
    public RESTServer registerComponent(Class<? extends Component> componentClass) {
        for (Method declaredMethod : componentClass.getDeclaredMethods()) {
            if (
                    declaredMethod.getParameterCount() == 1 &&
                            declaredMethod.getParameters()[0].getType() == Request.class
            ) {
                // Garbage code some1 pls fix it
                if (declaredMethod.isAnnotationPresent(Get.class)) {
                    components.add(new FunctionalComponent(FunctionalComponent.Type.GET,
                            declaredMethod.getAnnotation(Get.class).value(), declaredMethod));
                } else if (declaredMethod.isAnnotationPresent(Post.class)) {
                    components.add(new FunctionalComponent(FunctionalComponent.Type.POST,
                            declaredMethod.getAnnotation(Post.class).value(), declaredMethod));
                } else if (declaredMethod.isAnnotationPresent(Put.class)) {
                    components.add(new FunctionalComponent(FunctionalComponent.Type.PUT,
                            declaredMethod.getAnnotation(Put.class).value(), declaredMethod));
                } else if (declaredMethod.isAnnotationPresent(Delete.class)) {
                    components.add(new FunctionalComponent(FunctionalComponent.Type.DELETE,
                            declaredMethod.getAnnotation(Delete.class).value(), declaredMethod));
                }
            }
        }
        return this;
    }

    @Override
    public void publish(int port) throws IOException {
        for (FunctionalComponent component : components) {
            System.out.println("[DEBUG]"+component.getPath()+"|"+component.getType());
            server.createContext(component.getPath(),
                exchange -> {
                    try {
                        if (exchange.getRequestMethod().equals(component.getType().toString())) {
                            component.getMethod().invoke(null, new Request(exchange));
                        }
                    } catch (Exception e) {
                        throw new RESTServerException("Unknown error has occurred");
                    }
                });
        }
        server.bind(new InetSocketAddress(port), 0);
        server.start();
    }

    @Override
    public void stop() {
        server.stop(100);
    }
}
