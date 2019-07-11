package dev.fvames.starter.format;

/**
 *
 * @version 2019/7/11 16:51
 */

public class JsonFormatProcess implements FormatProcess{

	@Override
	public <T> String format(T obj) {
		return "JsonFormatProcess: "+ obj;
	}
}
