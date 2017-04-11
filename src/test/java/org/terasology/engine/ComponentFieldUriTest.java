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
package org.terasology.engine;

import org.junit.BeforeClass;
import org.junit.Test;
import org.terasology.naming.Name;
import org.terasology.testUtil.ApiTestUtils;
import org.terasology.testUtil.ApiTestParams;
import org.terasology.testUtil.MethodSignature;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/**
 * API test for class org.terasology.engine.ComponentFieldUri.
 */
public class ComponentFieldUriTest {

    private static Class<ComponentFieldUri> cl;

    private static Set<MethodSignature> registeredMethods;

    private static Set<MethodSignature> registeredConstructors;

    @BeforeClass
    public static void setupClass() {
        cl = ComponentFieldUri.class;
        registeredMethods = ApiTestParams.ObjectClassMethods;
        registeredMethods.add(new MethodSignature("getObjectName",Modifier.PUBLIC,null,Name.class,null));
        registeredMethods.add(new MethodSignature("getModuleName",Modifier.PUBLIC,null,Name.class,null));
        registeredMethods.add(new MethodSignature("getComponentUri",Modifier.PUBLIC,null,SimpleUri.class,null));
        registeredMethods.add(new MethodSignature("getFieldName", Modifier.PUBLIC,null,String.class,null));
        registeredMethods.add(new MethodSignature("isValid",Modifier.PUBLIC,null,boolean.class,null));

        registeredConstructors = new HashSet<>();
        registeredConstructors.add(new MethodSignature("ComponentFieldUri",Modifier.PUBLIC,new Class[]{SimpleUri.class,String.class},null));
        registeredConstructors.add(new MethodSignature("ComponentFieldUri",Modifier.PUBLIC,new Class[]{String.class},null));
    }

    @Test
    public void testConstructors() {
        ApiTestUtils.compareConstructors(cl,registeredConstructors);
    }

    @Test
    public void testMethodsSignature() {
        ApiTestUtils.compareMethods(cl,registeredMethods);
    }
}
