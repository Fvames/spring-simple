package dev.fvames.starterclient.web;

import dev.fvames.starter.HelloFormatTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @version 2019/7/11 17:15
 */
@RestController
public class FormatController {

	@Autowired
	private HelloFormatTemplate helloFormatTemplate;

	@PostMapping("/format")
	public String format(String msg) {
		return helloFormatTemplate.doFormat(msg);
	}
}
