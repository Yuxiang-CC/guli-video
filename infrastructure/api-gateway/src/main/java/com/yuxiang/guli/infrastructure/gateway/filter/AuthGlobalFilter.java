package com.yuxiang.guli.infrastructure.gateway.filter;

import com.google.gson.JsonObject;
import com.yuxiang.guli.common.base.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author: yuxiang
 * @create: 2020-07-06
 * @Description:
 **/
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        AntPathMatcher antPathMatcher = new AntPathMatcher();
        // 判断路径中 存在 /api/**/auth/** 的需要鉴权
        URI uri = request.getURI();
        String path = uri.getPath();

        if (antPathMatcher.match("/api/**/auth/**", path)) {
            // 获取token
            List<String> token = request.getHeaders().get("token");

            // 没有令牌
            if (token == null) {
                return out(response);
            }
            boolean isCheck = JWTUtils.checkJWT(token.get(0));
            // 令牌校验失败
            if (isCheck) {
                return out(response);
            }
        }

        return chain.filter(exchange);
    }

    private Mono<Void> out(ServerHttpResponse response) {

        JsonObject message = new JsonObject();
        message.addProperty("success", false);
        message.addProperty("code", 208004);
        message.addProperty("data", "");
        message.addProperty("message", "鉴权失败");
        byte[] bytes = message.toString().getBytes(StandardCharsets.UTF_8);
        DataBufferFactory dataBufferFactory = response.bufferFactory();
        DataBuffer buffer = dataBufferFactory.wrap(bytes);

        // 指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=utf-8");

        // 输出http响应
        return response.writeWith(Mono.just(buffer));
    }

    /**
     *  优先级，数越少优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }


}
