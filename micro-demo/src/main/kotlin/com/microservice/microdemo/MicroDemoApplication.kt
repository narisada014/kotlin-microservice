package com.microservice.microdemo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@SpringBootApplication
class MicroDemoApplication {
//	二つのbeanを見つけた場合例外が発生するのでConditionalOnExpressionを使用して環境別に定義する
	@Bean
	@ConditionalOnExpression("#{'\${service.message.type}'=='simple'}")
	fun exampleService() : ServiceInterface = ExampleService()

	@Bean
	@ConditionalOnExpression("#{'\${service.message.type}'=='advance'}")
	fun advanceService() : ServiceInterface = AdvanceService()
}

@Controller
class FirstController {
	@Autowired
	lateinit var service: ServiceInterface

	@RequestMapping(value = "/user/{name}", method = arrayOf(RequestMethod.GET))
	@ResponseBody
	fun hello(@PathVariable name: String) = service.getHello(name)
}

// これはClassをbeansから探してコントローラのパラメータとして使用するコーディング方法
// @Controller
// class FirstController(val exampleService: ExampleService) {
//  	@RequestMapping(value = "/user/{name}", method = arrayOf(RequestMethod.GET))
//		@ResponseBody
//		fun hello(@PathVariable name: String) = exampleService.getHello(name)
// }

fun main(args: Array<String>) {
	runApplication<MicroDemoApplication>(*args)
}
