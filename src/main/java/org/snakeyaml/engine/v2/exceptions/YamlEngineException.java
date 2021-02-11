/*
 * Copyright (c) 2018, http://www.snakeyaml.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.snakeyaml.engine.v2.exceptions;

/**
 * General exceptions
 */
public class YamlEngineException extends RuntimeException {
    public YamlEngineException(String message) {
        super(message);
    }

    public YamlEngineException(Throwable cause) {
        super(cause);
    }

    public YamlEngineException(String message, Throwable cause) {
        super(message, cause);
    }
}
