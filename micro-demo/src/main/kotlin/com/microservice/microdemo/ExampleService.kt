package com.microservice.microdemo

import org.springframework.beans.factory.annotation.Value
// import org.springframework.stereotype.Service

// @Serviceを削除してBeansを独自に注入している
class ExampleService : ServiceInterface {
    @Value(value = "\${service.message.text}")
    private lateinit var text: String

    override fun getHello(name: String) = "$text $name"
}