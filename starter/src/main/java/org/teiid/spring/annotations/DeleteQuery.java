/*
 * Copyright 2012-2014 the original author or authors.
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
package org.teiid.spring.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Defines the Teiid View's Transformation Delete query. This is optional annotation,
 * that can be defined only when you want support Delete on the view.<br>
 * 
 * 
 * <pre>
 * <code>
 * &#64;DeleteQuery(
 *   "FOR EACH ROW \n"+
 *   "BEGIN ATOMIC \n" +
 *       "DELETE FROM customerDS.person WHERE id = OLD.id;\n"+
 *   "END"
 *   )            
 * </code>
 * </pre>
 *
 * For more information checkout <a href=
 * "https://teiid.gitbooks.io/documents/content/reference/Update_Procedures_Triggers.html">Update
 * procedures</a> in Teiid.
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface DeleteQuery {
    String value() default "";
}
