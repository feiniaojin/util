package com.indeed.util.serialization.array;

import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;
import com.indeed.util.serialization.Stringifier;
import com.indeed.util.serialization.splitter.EscapeAwareSplitter;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author jplaisance
 */
public final class ByteArrayStringifier implements Stringifier<byte[]> {
    private static final Logger log = Logger.getLogger(ByteArrayStringifier.class);

    @Override
    public String toString(byte[] bytes) {
        return Arrays.toString(bytes);
    }

    @Override
    public byte[] fromString(String str) {
        List<Byte> bytes = Lists.newArrayList();
        EscapeAwareSplitter splitter = new EscapeAwareSplitter(CharMatcher.whitespace().or(CharMatcher.is(',')), EscapeAwareSplitter.NO_ESCAPE_LEXER_SUPPLIER);
        Iterator<String> split = splitter.split(str.substring(1, str.length()-1));
        while (split.hasNext()) {
            bytes.add(Byte.parseByte(split.next()));
        }
        byte[] ret = new byte[bytes.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = bytes.get(i);
        }
        return ret;
    }
}
