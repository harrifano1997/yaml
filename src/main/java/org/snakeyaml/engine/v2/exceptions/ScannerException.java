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

import org.snakeyaml.engine.v2.scanner.Scanner;

import java.util.Optional;

/**
 * Exception thrown by the {@link Scanner} implementations in case of malformed
 * input.
 */
public class ScannerException extends MarkedYamlEngineException { //NOSONAR

    /**
     * Constructs an instance.
     *
     * @param context     Part of the input document in which vicinity the problem
     *                    occurred.
     * @param contextMark Position of the <code>context</code> within the document.
     * @param problem     Part of the input document that caused the problem.
     * @param problemMark Position of the <code>problem</code> within the document.
     */
    public ScannerException(String context, Optional<Mark> contextMark, String problem, Optional<Mark> problemMark) {
        super(context, contextMark, problem, problemMark, null);
    }

    public ScannerException(String problem, Optional<Mark> problemMark) {
        super(null, Optional.empty(), problem, problemMark, null);
    }
}
