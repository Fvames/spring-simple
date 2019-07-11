package dev.fvames.starter;

import dev.fvames.starter.format.FormatProcess;

/**
 * @version 2019/7/11 16:46
 */

public class HelloFormatTemplate {

	private FormatProcess formatProcess;

	public HelloFormatTemplate(FormatProcess formatProcess) {
		this.formatProcess = formatProcess;
	}

	public <T> String doFormat(T obj) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("Hello, this is obj format result：").append(formatProcess.format(obj)).append("</br>");

		return stringBuilder.toString();
	}
}
