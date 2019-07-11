package dev.fvames.starter;

import dev.fvames.starter.format.FormatProcess;
import dev.fvames.starter.properties.HelloProperteies;

/**
 * @version 2019/7/11 16:46
 */

public class HelloFormatTemplate {

	private FormatProcess formatProcess;
	private HelloProperteies helloProperteies;

	public HelloFormatTemplate(HelloProperteies helloProperteies, FormatProcess formatProcess) {
		this.helloProperteies = helloProperteies;
		this.formatProcess = formatProcess;
	}

	public <T> String doFormat(T obj) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("hello properties: ").append(formatProcess.format(helloProperteies.getInfo())).append("</br>");
		stringBuilder.append("Hello, this is obj format result：").append(formatProcess.format(obj)).append("</br>");

		return stringBuilder.toString();
	}
}
