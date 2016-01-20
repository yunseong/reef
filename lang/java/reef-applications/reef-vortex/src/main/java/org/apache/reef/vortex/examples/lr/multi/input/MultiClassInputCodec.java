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
package org.apache.reef.vortex.examples.lr.multi.input;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.reef.io.serialization.Codec;
import org.apache.reef.vortex.common.HdfsCacheKey;
import org.apache.reef.vortex.common.MasterCacheKey;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Codec to serialize/deserialize DenseVector.
 */
public class MultiClassInputCodec implements Codec<MultiClassGradientFunctionInput> {

  @Override
  public byte[] encode(final MultiClassGradientFunctionInput obj) {
    final Kryo kryo = new Kryo();
    kryo.register(MasterCacheKey.class);
    kryo.register(HdfsCacheKey.class);

    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      try (final Output output = new Output(baos)) {
        kryo.writeObject(output, obj);
      }
      return baos.toByteArray();
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public MultiClassGradientFunctionInput decode(final byte[] buf) {
    final Kryo kryo = new Kryo();
    kryo.register(MasterCacheKey.class);
    kryo.register(HdfsCacheKey.class);
    try (final Input input = new Input(buf)) {
      final MultiClassGradientFunctionInput decoded = kryo.readObject(input, MultiClassGradientFunctionInput.class);
      return decoded;
    }
  }
}
