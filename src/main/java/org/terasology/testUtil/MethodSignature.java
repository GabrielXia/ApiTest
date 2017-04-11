/*
 * Copyright 2017 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.testUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Contains the signatures for a method (or a constructor) including method name, modifiers, parameters, return types and exceptions.
 */
public class MethodSignature {

    private String name;

    private int modifiers;

    private Class<?>[] parameterTypes;

    private Class<?> returnType;

    private Class<?>[] exceptionTypes;

    public MethodSignature(String name, int modifiers, Class<?>[] parameterTypes, Class<?> returnType, Class<?>[] exceptionTypes) {
        this.name = name;
        this.modifiers = modifiers;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes == null ? new Class[] {} : parameterTypes;
        this.exceptionTypes = exceptionTypes == null ? new Class[] {} : exceptionTypes;
    }

    public MethodSignature(Method method) {
        this.name = method.getName();
        this.modifiers = method.getModifiers();
        this.parameterTypes = method.getParameterTypes();
        this.returnType = method.getReturnType();
        this.exceptionTypes = method.getExceptionTypes();
    }

    /**
     * Method signature for a registered constructor
     * @param name the name of the constructor
     * @param modifiers the modifiers of the constructor
     * @param parameterTypes the parameters
     * @param exceptionTypes the array of the exceptions
     */
    public MethodSignature(String name, int modifiers, Class<?>[] parameterTypes, Class<?>[] exceptionTypes) {
        this.name = name;
        this.modifiers = modifiers;
        this.parameterTypes = parameterTypes == null ? new Class[] {} : parameterTypes;
        this.exceptionTypes = exceptionTypes == null ? new Class[] {} : exceptionTypes;
    }

    /**
     * Method siganture for a constructor
     * @param constructor the constructor
     */
    public MethodSignature(Constructor constructor) {
        String name = constructor.getName();
        String[] nameSplit = name.split("\\.");
        this.name = nameSplit[nameSplit.length-1];

        this.modifiers = constructor.getModifiers();
        this.parameterTypes = constructor.getParameterTypes();
        this.exceptionTypes = constructor.getExceptionTypes();
    }

    public String getName() {
        return name;
    }

    public int getModifiers() {
        return modifiers;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public Class<?>[] getExceptionTypes() {
        return exceptionTypes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof MethodSignature))return false;
        MethodSignature other = (MethodSignature) obj;
        if (name.equals(other.getName()) &&
                Arrays.equals(parameterTypes,other.getParameterTypes()) &&
                modifiers == other.getModifiers() &&
                objectEquals(returnType,other.getReturnType()) &&
                Arrays.equals(exceptionTypes,other.getExceptionTypes())
        ) {
            return true;
        } else {
            return false;
        }
    }

    private boolean objectEquals(Object o1, Object o2) {
        if (o1 == o2) return true;
        if ((o1 == null)||(o2 == null)) return false;
        return o1.equals(o2);
    }

    @Override
    public int hashCode() {
        int result = 7;
        int c = name.hashCode() * 7 +
                modifiers * 17 +
                (parameterTypes != null ? Arrays.hashCode(parameterTypes) : 0) * 29 +
                (returnType != null ? returnType.hashCode() : 0) * 31 +
                (exceptionTypes != null ? Arrays.hashCode(exceptionTypes) : 0) * 53;
        result = result * 37 + c;
        return result;
    }
}
