/*
 * Copyright (c) 2018, SnakeYAML
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.snakeyaml.engine.issues.issue51;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.snakeyaml.engine.v2.api.LoadSettings;
import org.snakeyaml.engine.v2.composer.Composer;
import org.snakeyaml.engine.v2.exceptions.ParserException;
import org.snakeyaml.engine.v2.parser.ParserImpl;
import org.snakeyaml.engine.v2.scanner.StreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * It looks like StreamReader:246 works strange. This code puts as the last char in the buffer the
 * NUL sign (as if it is a NUL terminated string in C)
 * <p>
 * int read = stream.read(buffer, 0, bufferSize - 1);
 */
@org.junit.jupiter.api.Tag("fast")
public class LoadSettingsTest {
    private final String yaml = " - foo: bar\n" + "   if: 'aaa' == 'bbb'";
    private final String expectedError = "while parsing a block mapping\n"
            + " in reader, line 1, column 4:\n"
            + "     - foo: bar\n"
            + "       ^\n"
            + "expected <block end>, but found '<scalar>'\n"
            + " in reader, line 2, column 14:\n"
            + "       if: 'aaa' == 'bbb'\n"
            + "                 ^\n";

  private void parse(String yaml, int size) {
    LoadSettings settings = LoadSettings.builder().setBufferSize(size).build();
    new Composer(settings, new ParserImpl(settings, new StreamReader(settings, yaml)))
        .getSingleNode();
  }


  @DisplayName("Issue 51 - exact buffer size")
  // @Test
  public void setBufferSizeCutsError() {
    ParserException exception =
        assertThrows(ParserException.class, () -> parse(yaml, yaml.length()));
    assertEquals(expectedError, exception.getMessage());
  }

  @DisplayName("Issue 51 - increased buffer size")
  @Test
  public void setBufferSizeDoesNotCutErrorWhen() {
    ParserException exception =
        assertThrows(ParserException.class, () -> parse(yaml, yaml.length() + 1));
    assertEquals(expectedError, exception.getMessage());
  }
}
