package two_methods.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
@Aspect
public class MyLogger {

    @Pointcut("execution(java.util.Map<String,Integer> two_methods.objects.Manager.*(..)) || execution(java.util.Map<String,Integer> two_methods.objects.FileManager2.*(..)) ")
    private void printMap() {
    }

    @Pointcut("execution(java.util.Map<String,Integer> two_methods.objects.Manager.*(..)) || execution(java.util.Map<String,Integer> two_methods.objects.FileManager2.*(..))")
    private void printTimeOnlyForReturningTypeMap() {
    }

    @Pointcut("execution(java.util.Set<String> two_methods.objects.Manager.*(..)) || execution(java.util.Set<String> two_methods.objects.FileManager2.*(..))")
    private void printSet() {
    }

    @Around("printTimeOnlyForReturningTypeMap()")
    public Object watchTime(ProceedingJoinPoint joinpoint) {
        long start = System.currentTimeMillis();
        System.out.println("method begin: " + joinpoint.getSignature().toShortString() + " >>");
        Object output = null;

        for (Object object : joinpoint.getArgs()) {
            System.out.println("Param : " + object);
        }

        try {
            output = joinpoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("method end: " + joinpoint.getSignature().toShortString() + ", time=" + time + " ms <<");
        System.out.println();

        return output;
    }

    @AfterReturning(pointcut = "printMap()", returning = "obj")
    public void printOnlyMap(Object obj) {

        System.out.println("Print map info begin >>");
        if (obj instanceof Map) {
            Map map = (Map) obj;
            for (Object object : map.keySet()) {
                System.out.println("key=" + object + ", " + map.get(object));
            }
        }
        System.out.println("Print map info end <<");
        System.out.println();
    }

    @AfterReturning(pointcut = "printSet()", returning = "obj")
    public void printOnlySet(Object obj) {

        System.out.println("Print set info begin >>");
        if (obj instanceof Set) {
            Set set = (Set) obj;
            for (Object object : set) {
                System.out.println(object);
            }
        }
        System.out.println("Print set info end <<");
        System.out.println();
    }
}
