package com.github.fnunezkanut.controller

import groovy.json.JsonSlurper
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

import java.util.concurrent.TimeUnit

@Controller
@EnableAutoConfiguration
class Info {


	@RequestMapping(
		value = "/info",
		method = RequestMethod.GET,
		produces = "application/json"
	)
	@ResponseBody
	HashMap<String, String> info() {

		final Logger logger = LoggerFactory.getLogger(Info.class)

		final OkHttpClient okHttpClient = new OkHttpClient()
		final OkHttpClient client = okHttpClient.newBuilder().readTimeout(6, TimeUnit.SECONDS).build()

		final String url = 'http://10.0.0.13:8080/info'


		final Request request = new Request.Builder().url(url).build()
		final Response response
		HashMap<String,String> json = new HashMap<String,String>()
		try {

			response = client.newCall(request).execute()
			json = new JsonSlurper().parseText( response.body().string() as String ) as HashMap<String,String>
		}
		catch (Exception e) {
			logger.error(e.getMessage())
		}
		finally {

			if (response != null) {

				response.body.close()
				response.close()
			}
		}

		final HashMap<String, String> message = json

		return message
	}

	//also get out of memory errors
	@RequestMapping(
		value = "/outofmemory2",
		method = RequestMethod.GET,
		produces = "application/json"
	)
	@ResponseBody
	HashMap<String, String> outofmemory2() {

		final Logger logger = LoggerFactory.getLogger(Info.class)
		final OkHttpClient client = new OkHttpClient.Builder().build()
		final String url = 'http://10.0.0.13:8080/info'


		final Request request = new Request.Builder().url(url).build()
		final Response response
		HashMap<String,String> json = new HashMap<String,String>()
		try {

			response = client.newCall(request).execute()
			json = new JsonSlurper().parseText( response.body().string() as String ) as HashMap<String,String>
		}
		catch (Exception e) {
			logger.error(e.getMessage())
		}
		finally {

			if (response != null) {

				response.body.close()
				response.close()
			}
		}

		final HashMap<String, String> message = json

		return message
	}


	//this use of okhttp runs out of memory fast
	@RequestMapping(
		value = "/outofmemory1",
		method = RequestMethod.GET,
		produces = "application/json"
	)
	@ResponseBody
	HashMap<String, String> outofmemory1() {

		final Logger logger = LoggerFactory.getLogger(Info.class)
		final OkHttpClient client = new OkHttpClient()
		final String url = 'http://10.0.0.13:8080/info'


		final Request request = new Request.Builder().url(url).build()
		final Response response
		HashMap<String,String> json = new HashMap<String,String>()
		try {

			response = client.newCall(request).execute()
			json = new JsonSlurper().parseText( response.body().string() as String ) as HashMap<String,String>
		}
		catch (Exception e) {
			logger.error(e.getMessage())
		}
		finally {

			if (response != null) {

				response.body.close()
				response.close()
			}
		}

		final HashMap<String, String> message = json

		return message
	}
}
