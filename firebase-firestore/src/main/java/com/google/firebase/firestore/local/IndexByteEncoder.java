// Copyright 2021 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.firebase.firestore.local;

import com.google.firebase.firestore.core.OrderBy;
import com.google.protobuf.ByteString;

public class IndexByteEncoder {

  private final OrderedCodeWriter orderedCode;
  private final DirectionalIndexByteEncoder ascendingEncoder = new AscendingEncoder();
  private final DirectionalIndexByteEncoder descendingEncoder = new DescendingEncoder();

  IndexByteEncoder() {
    this.orderedCode = new OrderedCodeWriter();
  }

  private class AscendingEncoder extends DirectionalIndexByteEncoder {

    @Override
    public void writeBytes(ByteString val) {
      orderedCode.writeBytesAscending(val);
    }

    @Override
    public void writeString(String val) {
      orderedCode.writeUtf8Ascending(val);
    }

    @Override
    public void writeLong(long val) {
      orderedCode.writeSignedLongAscending(val);
    }

    @Override
    public void writeDouble(double val) {
      orderedCode.writeDoubleAscending(val);
    }

    @Override
    public void writeNumber(double comparableNumberAsDouble) {
      orderedCode.writeNumberAscending(comparableNumberAsDouble);
    }

    @Override
    public void writeNumber(long comparableNumberAsLong) {
      orderedCode.writeNumberAscending(comparableNumberAsLong);
    }
  }

  private class DescendingEncoder extends DirectionalIndexByteEncoder {

    @Override
    public void writeBytes(ByteString val) {
      orderedCode.writeBytesDescending(val);
    }

    @Override
    public void writeString(String val) {
      orderedCode.writeUtf8Descending(val);
    }

    @Override
    public void writeLong(long val) {
      orderedCode.writeSignedLongDescending(val);
    }

    @Override
    public void writeDouble(double val) {
      orderedCode.writeDoubleDescending(val);
    }

    @Override
    public void writeNumber(double comparableNumberAsDouble) {
      orderedCode.writeNumberDescending(comparableNumberAsDouble);
    }

    @Override
    public void writeNumber(long comparableNumberAsLong) {
      orderedCode.writeNumberDescending(comparableNumberAsLong);
    }
  }

  public byte[] getEncodedBytes() {
    return orderedCode.encodedBytes();
  }

  public void reset() {
    orderedCode.reset();
  }

  public DirectionalIndexByteEncoder forDirection(OrderBy.Direction direction) {
    switch (direction) {
      case ASCENDING:
        return ascendingEncoder;
      case DESCENDING:
        return descendingEncoder;
    }
    throw new IllegalArgumentException("Unexpected direction " + direction);
  }
}
