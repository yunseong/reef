/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.reef.vortex.examples.lr.vector;

import java.io.Serializable;

/**
 * Implementation of sparse vector using arrays.
 * This is only used in Worker-side; no codec is required since this is not sent across the wire.
 */
public final class SparseVector implements Serializable {
  private float[] values;
  private int[] indices;

  private SparseVector() {
  }

  public SparseVector(final float[] values, final int[] indices) {
    this.values = values;
    this.indices = indices;
  }

  public int[] getIndices() {
    return indices;
  }

  public float[] getValues() {
    return values;
  }
}
