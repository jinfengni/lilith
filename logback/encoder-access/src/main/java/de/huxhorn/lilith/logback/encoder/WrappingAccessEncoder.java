/*
 * Lilith - a log event viewer.
 * Copyright (C) 2007-2017 Joern Huxhorn
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Copyright 2007-2017 Joern Huxhorn
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

package de.huxhorn.lilith.logback.encoder;

import ch.qos.logback.access.spi.AccessEvent;
import de.huxhorn.lilith.data.access.logback.LogbackAccessConverter;
import de.huxhorn.lilith.data.access.protobuf.CompressingAccessEventWrapperProtobufCodec;
import de.huxhorn.lilith.data.eventsource.EventWrapper;
import de.huxhorn.sulky.codec.Codec;

public class WrappingAccessEncoder
	implements ResettableEncoder<AccessEvent>
{
	private LogbackAccessConverter converter = new LogbackAccessConverter();
	private Codec<EventWrapper<de.huxhorn.lilith.data.access.AccessEvent>> codec = new CompressingAccessEventWrapperProtobufCodec();
	private long id;

	public void reset()
	{
		id=0;
	}

	public byte[] encode(AccessEvent event)
	{
		de.huxhorn.lilith.data.access.AccessEvent lilithEvent = converter.convert(event);
		EventWrapper<de.huxhorn.lilith.data.access.AccessEvent> wrapped=new EventWrapper<>();
		wrapped.setEvent(lilithEvent);
		//wrapped.setEventIdentifier();
		id++;
		wrapped.setLocalId(id);
		//wrapped.setSourceIdentifier();

		return codec.encode(wrapped);
	}
}
