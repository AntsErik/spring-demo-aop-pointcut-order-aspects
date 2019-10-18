package ee.praktika.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    //this is an aspect / java class that has a collection of advices, before and after and so on..

    //let us start with a before advice

    @Pointcut( "execution(* ee.praktika.aopdemo.dao.*.*(..))" )
    private void referencePointcut(){
    }

    //creating pointcut for getter methods
    @Pointcut( "execution(* ee.praktika.aopdemo.dao.*.get*(..))" )
    private void referenceGettersPointcut(){
    }

    //creating pointcut for setter methods
    @Pointcut( "execution(* ee.praktika.aopdemo.dao.*.set*(..))" )
    private void referenceSettersPointcut(){
    }

    //creating pointcut: include package, but exclude all .get and.set methods.
    @Pointcut( "referencePointcut() && !(referenceGettersPointcut() || referenceSettersPointcut())" )
    private void referencePointcutIgnoreGetSet(){
    }

    @Before( "referencePointcutIgnoreGetSet()" )
    public void beforeAddAccountAdvice(){
        System.out.println( "\n======>>> Executing @Before advice on addAccount() in the DAO package" );
    }

    @Before( "referencePointcutIgnoreGetSet()" )
    public void performApiAnalytics(){
        System.out.println( "\n======>>> Doing some cool analytics down here." );
    }
}
