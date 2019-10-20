package com.microservice.microdemo2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class Microdemo2Application {
	// Javaのstatic methodと同様な使い方ができる
	// Javaでstaticメソッドを作る場合はRubyでいうクラスメソッド的な位置付けになる。
	// いちいちインスタンスを作成し、メモリを利用する必要がないものはstaticで呼び出せばいい
	// また、staticメソッドを呼ぶものはstatic オブジェクトでなければならず型的な使い方もできる。
	companion object {
	    val initialCustomers = arrayOf(Customer(1, "Kotlin"),
				Customer(2, "Spring"),
				Customer(3, "Microservice"))
	}

	@Bean
	// initialCustomerのオブジェクトをCustomerオブジェクトのidで結びつける。
	// ConcurrentHashMap<Int, String>()を行うと{}が生成される。()内の引数はarrayOfで定義されたオブジェクト
	// Customerのidとハッシュ配列のインデックスIdが紐づけられる => {1=Customer(id=1, name=kotlin)} こんな感じ
	// かつ {1=Customer(id=1, name=gggg)}の形式のデータのみをとるという意味
	fun customers() = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))
}

fun main(args: Array<String>) {
	runApplication<Microdemo2Application>(*args)
}
