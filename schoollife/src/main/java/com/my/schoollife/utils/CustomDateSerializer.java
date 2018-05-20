package com.my.schoollife.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.map.JsonSerializer;

public class CustomDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, org.codehaus.jackson.JsonGenerator jsonGenerator,
			org.codehaus.jackson.map.SerializerProvider arg2)
			throws IOException, org.codehaus.jackson.JsonProcessingException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		jsonGenerator.writeString(sdf.format(value));
	}

}
