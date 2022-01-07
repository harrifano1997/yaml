/*
 * Copyright (c) 2018, SnakeYAML
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
package org.snakeyaml.engine.v2.common;

import java.util.ArrayList;

/**
 * General stack implementation.
 */
public class ArrayStack<T> {

  private final ArrayList<T> stack;

  /**
   * Create empty stack
   *
   * @param initSize - the initial size of the stack
   */
  public ArrayStack(int initSize) {
    stack = new ArrayList<>(initSize);
  }

  public void push(T obj) {
    stack.add(obj);
  }

  public T pop() {
    return stack.remove(stack.size() - 1);
  }

  public boolean isEmpty() {
    return stack.isEmpty();
  }
}