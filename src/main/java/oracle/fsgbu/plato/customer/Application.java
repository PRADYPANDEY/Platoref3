package oracle.fsgbu.plato.customer;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan({"oracle.fsgbu.plato.customer", "oracle.fsgbu.plato.common",
    "oracle.fsgbu.plato.core", "oracle.fsgbu.plato.logger"})
@EnableEurekaClient
/*@EnableFeignClients*/
@RestController
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {

  private static final Logger LOG = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    LOG.info("Starting up the demo {} application");
    SpringApplication.run(Application.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(Application.class);
  }
  
  @RequestMapping("/headers")
  public String getHeaders(HttpServletRequest request) {
	  return request.getHeaders("appId").toString();
  }
  
/*  @Autowired
  private PlatoGatewayRefClient platoGatewayRefClient;
 
  @RequestMapping("/feign")
  public ResponseEntity<String> run() throws Exception {
      System.out.println(this.platoGatewayRefClient.message());
      ResponseEntity<String> responseDetail = new ResponseEntity<>(this.platoGatewayRefClient.message(), HttpStatus.OK);
      return responseDetail;
  }*/
}

/*@FeignClient("plato-api-gateway-ref")
interface PlatoGatewayRefClient {
  @RequestMapping(method = RequestMethod.GET, value = "plato-api-gateway-ref/message")
  String message();
}*/
