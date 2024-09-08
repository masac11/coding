package com.coding.design;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

enum Role {
    LEADER, NORMAL
}

class Employee {
    private String name;
    private String phone;

}

interface FieldAccessChecker {
    boolean canAccessField(Role role, String fieldName);
}

class SimpleFieldAccessChecker implements FieldAccessChecker {
    @Override
    public boolean canAccessField(Role role, String fieldName) {
        if (role == Role.LEADER) {
            return true; // 领导可以访问所有字段
        }
        return !"phone".equals(fieldName); // 普通人不能访问手机号
    }
}

class EmployeeProxyHandler implements InvocationHandler {
    private Employee target;
    private FieldAccessChecker checker;

    public EmployeeProxyHandler(Employee target, FieldAccessChecker checker) {
        this.target = target;
        this.checker = checker;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
            String fieldName = method.getName().substring(3); // 假设所有getter都遵循JavaBean规范
            fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1); // 转换为字段名
            if (!checker.canAccessField(Role.NORMAL, fieldName)) { // 假设默认角色为NORMAL
                throw new SecurityException("Access denied for field: " + fieldName);
            }
        }
        return method.invoke(target, args);
    }

    public static Employee createProxy(Employee target, Role role, FieldAccessChecker checker) {
        return (Employee) Proxy.newProxyInstance(
                Employee.class.getClassLoader(),
                new Class<?>[]{Employee.class},
                new EmployeeProxyHandler(target, checker)
        );
    }
}

public class RABC {
}
