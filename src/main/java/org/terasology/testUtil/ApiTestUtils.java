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

import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Some static methods for API test.
 */
public class ApiTestUtils {

    /**
     * Get all public and protected methods from a class.
     * @param cl the target class.
     * @return the set of all public and protected methods.
     */
    public static Set<Method> getPublicAndProtectedMethods(Class<?> cl) {
        Set<Method> methods = new HashSet<>();
        Collections.addAll(methods, cl.getMethods());
        Set<Object> methodTypes = new HashSet<>();
        for(Method m: methods) {
            methodTypes.add(methodKey(m));
        }

        for(Class<?> current=cl; current!=null; current=current.getSuperclass()) {
            for(Method m: current.getDeclaredMethods()) {
                int mod = m.getModifiers();
                if (Modifier.isProtected(mod)) {
                    if (methodTypes.add(methodKey(m)) == false) {
                        continue;
                    }
                    methods.add(m);
                }
            }
        }
        return methods;
    }

    private static Object methodKey(Method m) {
        return Arrays.asList(m.getName(),
                MethodType.methodType(m.getReturnType(), m.getParameterTypes()));
    }

    /**
     * Print all public and protected methods from a class.
     * @param cl the target class.
     */
    public static void printPublicAndProtectedMethods(Class<?> cl) {
        Set<Method> publicAndProtectedMethods = getPublicAndProtectedMethods(cl);
        for (Method method : publicAndProtectedMethods) {
            System.out.println(method.toString());
        }
    }

    /**
     * Compare method signatures between all public & protected methods from target class and methods registered.
     * @param cl the target class.
     * @param registeredMethods all public & protected methods registered in API test.
     */
    public static void compareMethods(Class cl, Set<MethodSignature> registeredMethods) {
        Set<Method> currentMethods = ApiTestUtils.getPublicAndProtectedMethods(cl);
        assertEquals("Methods number changes",currentMethods.size(), registeredMethods.size());
        for (Method m : currentMethods) {
            MethodSignature methodSignatureForM = new MethodSignature(m);
            assertTrue(String.format("Method signature changes:\n%s",m.toString()), registeredMethods.contains(methodSignatureForM));
        }
    }

    /**
     * Get all public and protected constructors.
     * @param cl the target class.
     * @return the set of all public & protected constructors.
     */
    public static Set<Constructor> getPublicAndProtectedConstructors(Class<?> cl) {
        Set<Constructor> constructors = new HashSet<>();
        Constructor[] ctors = cl.getDeclaredConstructors();
        for (Constructor ctor : ctors) {
            int mod = ctor.getModifiers();
            if (Modifier.isPrivate(mod)) {
                continue;
            }
            constructors.add(ctor);
        }
        return constructors;
    }

    /**
     * Print all public & protected constructors.
     * @param cl the target class.
     */
    public static void printPublicAndProtectedConstructor(Class<?> cl) {
        Set<Constructor> publicAndProtectedConstructors = getPublicAndProtectedConstructors(cl);
        for (Constructor constructor : publicAndProtectedConstructors) {
            System.out.println(constructor.toString());
        }
    }

    /**
     * Compare the signatures between all public & protected constructors and registered constructors.
     * @param cl the target class.
     * @param registeredConstructors registered constructors of the target class.
     */
    public static void compareConstructors(Class cl, Set<MethodSignature> registeredConstructors) {
        Set<Constructor> currentConstructors = ApiTestUtils.getPublicAndProtectedConstructors(cl);
        assertEquals("Methods number changes",currentConstructors.size(), registeredConstructors.size());
        for (Constructor ctor : currentConstructors) {
            MethodSignature ctorSignature = new MethodSignature(ctor);
            assertTrue(String.format("Method signature changes:\n%s",ctor.toString()), registeredConstructors.contains(ctorSignature));
        }
    }
}
