package com.manjan.webcrawler;

import com.manjan.webcrawler.service.WebCrawlerService;
import com.manjan.webcrawler.vo.WebCrawlerVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebCrawlerApplication extends SpringBootServletInitializer {
	private static final Logger logger = LoggerFactory.getLogger(WebCrawlerApplication.class);

	@Autowired
	private WebCrawlerService webCrawlerService;


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebCrawlerApplication.class);
	}

	public static void main(String[] args) {
		logger.info("WebCrawlerApplication - START");
		SpringApplication.run(WebCrawlerApplication.class, args);
		logger.info("WebCrawlerApplication - END");
	}

	@RequestMapping(value = "/webcrawler",produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
	public WebCrawlerVO WebCrawler(@RequestBody WebCrawlerVO request)throws Throwable {
		return webCrawlerService.WebCrawler(request);
	}

}
