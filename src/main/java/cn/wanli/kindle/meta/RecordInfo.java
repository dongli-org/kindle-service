/*
 * kindle electronic book push service
 *
 * Copyright (C) 2018 - wanli <wanlinus@qq.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.wanli.kindle.meta;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RecordInfo {
    private byte[] recordDataOffset = {0, 0, 0, 0};
    private byte recordAttributes = 0;
    private byte[] uniqueID = {0, 0, 0};

    public RecordInfo(InputStream in)
            throws IOException {
        StreamUtils.readByteArray(in, recordDataOffset);
        recordAttributes = StreamUtils.readByte(in);
        StreamUtils.readByteArray(in, uniqueID);

        if (MobiCommon.debug) {
            MobiCommon.logMessage("RecordInfo uniqueID: "
                    + StreamUtils.byteArrayToInt(uniqueID));
        }
    }

    public long getRecordDataOffset() {
        return StreamUtils.byteArrayToLong(recordDataOffset);
    }

    public void setRecordDataOffset(long newOffset) {
        StreamUtils.longToByteArray(newOffset, recordDataOffset);
    }

    public void write(OutputStream out) throws IOException {
        out.write(recordDataOffset);
        out.write(recordAttributes);
        out.write(uniqueID);
    }
}
