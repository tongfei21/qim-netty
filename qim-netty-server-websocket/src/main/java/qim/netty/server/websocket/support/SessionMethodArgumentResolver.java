package qim.netty.server.websocket.support;

import io.netty.channel.Channel;
import org.springframework.core.MethodParameter;
import qim.netty.server.websocket.pojo.PojoEndpointServer;
import qim.netty.server.websocket.pojo.Session;

public class SessionMethodArgumentResolver implements MethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Session.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, Channel channel, Object object) throws Exception {
        Session session = channel.attr(PojoEndpointServer.SESSION_KEY).get();
        return session;
    }
}
