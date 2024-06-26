/*
 * Copyright (c) 2023 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.integrations.debezium;

import io.airbyte.integrations.debezium.internals.AirbyteSchemaHistoryStorage.SchemaHistory;
import io.airbyte.protocol.models.v0.AirbyteMessage;
import java.util.Map;

/**
 * This interface is used to allow connectors to save the offset and schema history in the manner
 * which suits them. Also, it adds some utils to verify CDC event status.
 */
public interface CdcStateHandler {

  AirbyteMessage saveState(final Map<String, String> offset, final SchemaHistory<String> dbHistory);

  AirbyteMessage saveStateAfterCompletionOfSnapshotOfNewStreams();

  default boolean compressSchemaHistoryForState() {
    return false;
  }

  /**
   * This function is used as feature flag for sending state messages as checkpoints in CDC syncs.
   *
   * @return Returns `true` if checkpoint state messages are enabled for CDC syncs. Otherwise, it
   *         returns `false`
   */
  default boolean isCdcCheckpointEnabled() {
    return false;
  }

}
