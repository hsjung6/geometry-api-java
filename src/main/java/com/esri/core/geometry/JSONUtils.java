/*
 Copyright 1995-2015 Esri

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 For additional information, contact:
 Environmental Systems Research Institute, Inc.
 Attn: Contracts Dept
 380 New York Street
 Redlands, California, USA 92373

 email: contracts@esri.com
 */
package com.esri.core.geometry;

import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonToken;

final class JSONUtils {

	static boolean isObjectStart(JsonReader parser) throws Exception {
		return parser.currentToken() == null ? parser.nextToken() == JsonToken.START_OBJECT
				: parser.currentToken() == JsonToken.START_OBJECT;
	}

	static double readDouble(JsonReader parser) throws JsonParseException,
			IOException, Exception {
		if (parser.currentToken() == JsonToken.VALUE_NUMBER_FLOAT)
			return parser.currentDoubleValue();
		else if (parser.currentToken() == JsonToken.VALUE_NUMBER_INT)
			return parser.currentIntValue();
		else if (parser.currentToken() == JsonToken.VALUE_NULL)
			return NumberUtils.NaN();
		else if (parser.currentToken() == JsonToken.VALUE_STRING)
			if (parser.currentString().equals("NaN"))
				return NumberUtils.NaN();

		throw new GeometryException("invalid parameter");
	}

}
