package com.wissensalt.rnd.springbootsoap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WsConfig extends WsConfigurerAdapter {

	/**
	 * <p>
	 * 		Message Dispatcher Servlet
	 * 		Application Context
	 *		url -> /ws/*
	 * </p>
	 * @param p_ApplicationContext
	 * @return
	 */
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext p_ApplicationContext) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(p_ApplicationContext);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	}
	
	
	/**
	 * <p>
	 * 		/ws/employee.wsdl
	 * 		EmployeePort
	 * 		NameSpace : http://wissensalt.com/Employee
	 * </p>
	 * @param p_XsdSchema
	 * @return
	 */
	@Bean(name="employee")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema p_XsdSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("EmployeePort");		
		definition.setLocationUri("/ws");
		definition.setTargetNamespace("http://wissensalt.com/Employee");
		definition.setSchema(p_XsdSchema);
		return definition;
	}
	
	/**
	 * <p>
	 * 		Employee.xsd
	 * </p>
	 * @return
	 */
	@Bean
	public XsdSchema employeeSchema() {
		return new SimpleXsdSchema(new ClassPathResource("Employee.xsd"));
	}

	/**
	 * <p>
	 *     XwsSecurityInterception
	 *
	 * </p>
	 *
	 */
	/*@Bean
	public XwsSecurityInterceptor securityInterceptor () {
		XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
		securityInterceptor.setCallbackHandler(callBackHandler());
		securityInterceptor.setPolicyConfiguration(new ClassPathResource("security-policy.xml"));
		return securityInterceptor;
	}

	@Bean
	public SimplePasswordValidationCallbackHandler callBackHandler() {
		SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
		handler.setUsersMap(Collections.singletonMap("user", "password"));
		return handler;
	}

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(securityInterceptor());
	}*/
}
