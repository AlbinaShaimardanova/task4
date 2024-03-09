package org.example.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class LogTransformationA {

    @Value("${log.file_path}")
    private String logPath;

    @Pointcut("within(@LogTransformation *)")
    public void logTransformation() {
    }

    @Around("logTransformation()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        var methodSignature = (MethodSignature) joinPoint.getSignature();
        var className = methodSignature.getDeclaringType().getSimpleName();
        var methodName = methodSignature.getName();

        var method = methodSignature.getMethod();
        var declaringClass = method.getDeclaringClass();
        var logTransformation = declaringClass.getAnnotation(LogTransformation.class);
        var filePath = logPath + "\\" + logTransformation.value();

        var start = LocalDateTime.now();
        var proceed = joinPoint.proceed();
        var finish = LocalDateTime.now();

        var buf = new BufferedWriter(new FileWriter(filePath, true));
        buf.write("Method name: " + className + "." + methodName + "; " +
                "start: " + start + "; " +
                "stop: " + finish + "; " +
                "input parameters: " + Arrays.toString(joinPoint.getArgs()) + "; " +
                "output result: " + proceed + "; ");
        buf.newLine();
        buf.close();

        return proceed;
    }
}
