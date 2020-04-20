package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration	//表示是配置类，要被加载
@EnableSwagger2 //开启swagger2
public class SwaggerConfig {
	
	//用来设置swagger的值是否关闭
	private boolean swaggerSwitch = false;
	
	//配置了Swagger的Dockeer的bean实例
	 @Bean
	 public Docket createRestApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	        		.apiInfo(apiInfo())
	        		.groupName("swagger")
	        		//enable是否启动Swagger，如果为false，则swagger不能在浏览器中访问
	        		.enable(true)
	        		.select()
	        		//RequestHandlerSelectors,配置要扫描接口的方式
	        		//basePackage：制定要扫描的包
	        		//any()扫描全部
	        		//none():不扫描
	        		.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
	        		//paths() 过滤什么路径
	        		//.paths(PathSelectors.ant("/example/**"))
	        		.build();
	 }
	 
	 //配置多个分组协同开发
	 @Bean
	 public Docket docket1() {
		 //A分组通过swaggerSwitch控制是否启用
	     return new Docket(DocumentationType.SWAGGER_2).groupName("A").enable(swaggerSwitch);
	 }
	 
	 @Bean
	 public Docket docket2() {
		 return new Docket(DocumentationType.SWAGGER_2).groupName("B");
	 }

	 //api基本信息的配置，信息会在api文档上显示，可有选择的填充，比如配置文档名称、项目版本号等
	 private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	                .title("Swagger api示例文档")
	                .description("号称世界上最流行的api框架")
	                .version("1.0.0")
	                .build();
	}
}
