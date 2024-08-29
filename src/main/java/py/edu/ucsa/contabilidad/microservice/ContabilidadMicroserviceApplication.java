package py.edu.ucsa.contabilidad.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(scanBasePackages = {"py.edu.ucsa.contabilidad.api.web.controllers",
										   "py.edu.ucsa.contabilidad.api.core.services",
										   "py.edu.ucsa.contabilidad.api.core.dao"})
@EnableAutoConfiguration
@Import(JpaConfiguration.class)
@EnableTransactionManagement
public class ContabilidadMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContabilidadMicroserviceApplication.class, args);
	}

}

