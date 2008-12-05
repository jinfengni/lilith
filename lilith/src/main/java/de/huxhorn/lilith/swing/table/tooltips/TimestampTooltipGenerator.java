/*
 * Lilith - a log event viewer.
 * Copyright (C) 2007-2008 Joern Huxhorn
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.huxhorn.lilith.swing.table.tooltips;

import de.huxhorn.lilith.swing.table.TooltipGenerator;
import de.huxhorn.lilith.data.eventsource.EventWrapper;

import javax.swing.JTable;

import de.huxhorn.lilith.data.logging.LoggingEvent;
import de.huxhorn.lilith.data.access.AccessEvent;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TimestampTooltipGenerator
	implements TooltipGenerator
{
	private SimpleDateFormat fullFormat;

	public TimestampTooltipGenerator()
	{
		fullFormat=new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss.SSS");

	}

	public String createTooltipText(JTable table, int row)
	{
		String tooltip=null;
		Object value=table.getValueAt(row,0);
		if(value instanceof EventWrapper)
		{
			EventWrapper wrapper=(EventWrapper)value;
			Object eventObj = wrapper.getEvent();
			if(eventObj instanceof LoggingEvent)
			{
				LoggingEvent event=(LoggingEvent) eventObj;
				Date timestamp=event.getTimeStamp();
				tooltip=fullFormat.format(timestamp);

			}
			else if(eventObj instanceof AccessEvent)
			{
				AccessEvent event=(AccessEvent) eventObj;
				Date timestamp=event.getTimeStamp();
				tooltip=fullFormat.format(timestamp);
			}
		}
		return tooltip;
	}

}