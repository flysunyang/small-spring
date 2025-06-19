package org.springframework.aop.aspectj;

import lombok.Setter;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    
    @Setter
    private Advice advice;
    
    private AspectJExpressionPointcut pointcut;

    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public AspectJExpressionPointcutAdvisor() {
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}
