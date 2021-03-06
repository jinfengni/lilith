/*
 * Lilith - a log event viewer.
 * Copyright (C) 2007-2017 Joern Huxhorn
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

package de.huxhorn.lilith.conditions;

import java.util.Map;

public abstract class AbstractStringStringArrayMapContainsCondition
	extends AbstractMapCondition<String[]>
	implements Cloneable
{
	private static final long serialVersionUID = 4491831858516151038L;

	AbstractStringStringArrayMapContainsCondition()
	{
		super();
	}

	AbstractStringStringArrayMapContainsCondition(String key, String value)
	{
		super(key, value);
	}

	protected abstract Map<String, String[]> resolveMap(Object element);

	/**
	 * Only called if getValue() is not null.
	 *
	 * @param mapValue the value to compare against value.
	 * @return true, if this condition matches.
	 */
	protected boolean isTrueForValue(String[] mapValue)
	{
		if(mapValue == null)
		{
			return false;
		}
		String value = getValue();
		for (String currentValue : mapValue)
		{
			if(value.equals(currentValue))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public AbstractStringStringArrayMapContainsCondition clone() throws CloneNotSupportedException {
		return (AbstractStringStringArrayMapContainsCondition) super.clone();
	}

	public abstract String getDescription();
}
