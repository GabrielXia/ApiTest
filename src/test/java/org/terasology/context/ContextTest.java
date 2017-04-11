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
package org.terasology.context;

import org.junit.BeforeClass;
import org.junit.Test;
import org.terasology.testUtil.ApiTestUtils;
import org.terasology.testUtil.MethodSignature;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/**
 * API test for interface org.terasology.context.Context.
 */
public class ContextTest {

    private static Class cl;

    private static Set<MethodSignature> registeredMethods;

    @BeforeClass
    public static void setupClass() {
        cl = Context.class;
        registeredMethods = new HashSet<MethodSignature>() ;
        registeredMethods.add(new MethodSignature("get", Modifier.PUBLIC|Modifier.ABSTRACT,new Class[] {Class.class},Object.class, null));
        registeredMethods.add(new MethodSignature("put",Modifier.PUBLIC|Modifier.ABSTRACT,new Class[]{Class.class,Object.class},void.class,null));
    }

    @Test
    public void methodsTest() {
        ApiTestUtils.compareMethods(cl,registeredMethods);
    }
}
