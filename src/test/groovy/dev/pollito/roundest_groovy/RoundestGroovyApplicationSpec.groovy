package dev.pollito.roundest_groovy

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class RoundestGroovyApplicationSpec extends Specification {

	def "context loads"() {
		expect: "the application context loads successfully"
		true // The test will pass as long as the context loads without exceptions
	}
}
