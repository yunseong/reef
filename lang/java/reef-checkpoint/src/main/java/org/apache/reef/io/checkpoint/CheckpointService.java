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
package org.apache.reef.io.checkpoint;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * The CheckpointService provides a simple API to store and retrieve the state of a task.
 * <p>
 * Checkpoints are atomic, single-writer, write-once, multiple-readers, ready-many type of objects.
 * This is provided by releasing the CheckpointID for a checkpoint only upon commit of the checkpoint,
 * and by preventing a checkpoint to be re-opened for writes.
 * <p>
 * Non-functional properties such as durability, availability, compression, garbage collection,
 * quotas are left to the implementation.
 * <p>
 * This API is envisioned as the basic building block for a checkpoint service, on top of which richer
 * interfaces can be layered (e.g., frameworks providing object-serialization, checkpoint metadata and
 * provenance, etc.)
 */
public interface CheckpointService {

  /**
   * This method creates a checkpoint and provide a channel to write to it.
   * The name/location of the checkpoint are unknown to the user as of this time, in fact,
   * the CheckpointID is not released to the user until commit is called. This makes enforcing
   * atomicity of writes easy.
   *
   * @return a CheckpointWriteChannel that can be used to write to the checkpoint
   * @throws IOException
   * @throws InterruptedException
   */
  CheckpointWriteChannel create() throws IOException, InterruptedException;

  /**
   * Used to finalize and existing checkpoint. It returns the CheckpointID that can be later
   * used to access (read-only) this checkpoint. This guarantees atomicity of the checkpoint.
   *
   * @param channel the CheckpointWriteChannel to commit
   * @return a CheckpointID
   * @throws IOException
   * @throws InterruptedException
   */
  CheckpointID commit(CheckpointWriteChannel channel) throws IOException, InterruptedException;

  /**
   * Dual to commit, it aborts the current checkpoint. Garbage collection choices are
   * left to the implementation. The CheckpointID is not generated nor released to the
   * user so the checkpoint is not accessible.
   *
   * @param channel the CheckpointWriteChannel to abort
   * @throws IOException
   * @throws InterruptedException
   */
  void abort(CheckpointWriteChannel channel) throws IOException, InterruptedException;

  /**
   * Given a CheckpointID returns a reading channel.
   *
   * @param checkpointId CheckpointID for the checkpoint to be opened
   * @return a CheckpointReadChannel
   * @throws IOException
   * @throws InterruptedException
   */
  CheckpointReadChannel open(CheckpointID checkpointId) throws IOException, InterruptedException;

  /**
   * It discards an existing checkpoint identified by its CheckpointID.
   *
   * @param checkpointId CheckpointID for the checkpoint to be deleted
   * @return a boolean confirming success of the deletion
   * @throws IOException
   * @throws InterruptedException
   */
  boolean delete(CheckpointID checkpointId) throws IOException, InterruptedException;

  /**
   * A channel to write to a checkpoint.
   */
  interface CheckpointWriteChannel extends WritableByteChannel {
  }

  /**
   * A channel to read from a checkpoint.
   */
  interface CheckpointReadChannel extends ReadableByteChannel {
  }
}
