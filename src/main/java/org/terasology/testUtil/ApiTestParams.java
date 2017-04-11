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

import com.google.common.collect.Sets;

import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * Some parameters for API test.
 */
public class ApiTestParams {

    /**
     * All public & protected methods in Object class.
     */
    public static final Set<MethodSignature> ObjectClassMethods = Sets.newHashSet(
            new MethodSignature("equals", Modifier.PUBLIC,new Class[] {Object.class},boolean.class,null),
            new MethodSignature("toString",Modifier.PUBLIC,null,String.class,null),
            new MethodSignature("hashCode",Modifier.PUBLIC,null,int.class,null),
            new MethodSignature("wait",Modifier.PUBLIC|Modifier.FINAL,new Class[]{long.class,int.class},void.class,new Class[]{InterruptedException.class}),
            new MethodSignature("wait",Modifier.PUBLIC|Modifier.FINAL|Modifier.NATIVE, new Class[]{long.class},void.class,new Class[]{InterruptedException.class}),
            new MethodSignature("wait", Modifier.PUBLIC|Modifier.FINAL,null, void.class, new Class[]{InterruptedException.class}),
            new MethodSignature("getClass",Modifier.PUBLIC|Modifier.FINAL|Modifier.NATIVE,null, Class.class,null),
            new MethodSignature("notify",Modifier.PUBLIC|Modifier.FINAL|Modifier.NATIVE,null,void.class,null),
            new MethodSignature("notifyAll",Modifier.PUBLIC|Modifier.FINAL|Modifier.NATIVE,null,void.class,null),
            new MethodSignature("finalize",Modifier.PROTECTED,null,void.class,new Class[]{Throwable.class}),
            new MethodSignature("clone",Modifier.PROTECTED|Modifier.NATIVE,null,Object.class,new Class[]{CloneNotSupportedException.class})
    );
}
