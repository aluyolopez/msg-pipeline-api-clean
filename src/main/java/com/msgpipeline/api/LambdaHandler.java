package com.msgpipeline.api;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.*;                    
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

// RequestHandler<Input, Output>: interfaz principal de Lambda.
// AwsProxyRequest: evento HTTP enviado por API Gateway (nombre correcto en v2.x).
// AwsProxyResponse: respuesta HTTP que Lambda devuelve a API Gateway.
public class LambdaHandler implements
        RequestHandler<AwsProxyRequest, AwsProxyResponse> {          

    
    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static {
        try {
            handler = SpringBootLambdaContainerHandler
                .getAwsProxyHandler(MsgPipelineApiApplication.class);
        } catch (ContainerInitializationException e) {
            throw new RuntimeException("Error al inicializar Spring Boot en Lambda", e);
        }
    }

    // Constructor público sin argumentos: OBLIGATORIO para que AWS instancie el handler.
    public LambdaHandler() {}

    @Override
    public AwsProxyResponse handleRequest(
            AwsProxyRequest event, Context context) {               
        return handler.proxy(event, context);
    }
}