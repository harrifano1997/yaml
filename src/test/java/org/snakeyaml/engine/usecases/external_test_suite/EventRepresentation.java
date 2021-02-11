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
package org.snakeyaml.engine.usecases.external_test_suite;

import com.google.common.base.Splitter;
import org.snakeyaml.engine.v2.events.AliasEvent;
import org.snakeyaml.engine.v2.events.CollectionStartEvent;
import org.snakeyaml.engine.v2.events.Event;
import org.snakeyaml.engine.v2.events.ImplicitTuple;
import org.snakeyaml.engine.v2.events.MappingStartEvent;
import org.snakeyaml.engine.v2.events.NodeEvent;
import org.snakeyaml.engine.v2.events.ScalarEvent;
import org.snakeyaml.engine.v2.events.SequenceStartEvent;
import org.snakeyaml.engine.v2.nodes.Tag;

import java.util.List;

/**
 * Event representation for the external test suite
 */
public class EventRepresentation {
    private final Event event;

    public EventRepresentation(Event event) {
        this.event = event;
    }

    public String getRepresentation() {
        return event.toString();
    }


    public boolean isSameAs(String data) {
        List<String> split = Splitter.on(' ').splitToList(data);
        if (!event.toString().startsWith(split.get(0))) return false;
        /*
        if (event instanceof DocumentStartEvent) {
            DocumentStartEvent e = (DocumentStartEvent) event;
            if (e.isExplicit()) {
                if (split.size() != 2 || !split.get(1).equals("---")) return false;
            } else {
                if (split.size() != 1) return false;
            }
        }
        if (event instanceof DocumentEndEvent) {
            DocumentEndEvent e = (DocumentEndEvent) event;
            if (e.isExplicit()) {
                if (split.size() != 2 || !split.get(1).equals("...")) return false;
            } else {
                if (split.size() != 1) return false;
            }
        }
        */
        if (event instanceof MappingStartEvent) {
            CollectionStartEvent e = (CollectionStartEvent) event;
            if (e.getTag().isPresent() && !Tag.MAP.getValue().equals(e.getTag().get())) {
                String last = split.get(split.size() - 1);
                if (!last.equals("<" + e.getTag().get() + ">")) return false;
            }
        }
        if (event instanceof SequenceStartEvent) {
            SequenceStartEvent e = (SequenceStartEvent) event;
            if (e.getTag().isPresent() && !Tag.SEQ.getValue().equals(e.getTag().get())) {
                String last = split.get(split.size() - 1);
                if (!last.equals("<" + e.getTag().get() + ">")) return false;
            }
        }
        if (event instanceof NodeEvent) {
            NodeEvent e = (NodeEvent) event;
            if (e.getAnchor().isPresent()) {
                if (event instanceof AliasEvent) {
                    if (!split.get(1).startsWith("*")) return false;
                } else {
                    if (!split.get(1).startsWith("&")) return false;
                }
            }
        }
        if (event instanceof ScalarEvent) {
            ScalarEvent e = (ScalarEvent) event;
            if (e.getTag().isPresent()) {
                String tag = e.getTag().get();
                ImplicitTuple implicit = e.getImplicit();
                if (implicit.bothFalse()) {
                    if (!data.contains("<" + e.getTag().get() + ">")) return false;
                }
            }
            String end = e.getScalarStyle() + e.escapedValue();
            return data.endsWith(end);
        }
        return true;
    }
}
