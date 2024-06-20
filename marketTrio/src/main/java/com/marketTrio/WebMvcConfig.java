package com.marketTrio;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//
//	@Autowired
//	@Qualifier(value = "signonInterceptor")
//	private HandlerInterceptor interceptor;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("thyme/groupBuy/groupBuyList");
		//registry.addViewController("/shop/signonForm.do").setViewName("SignonForm");
	}
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(interceptor)
//				.addPathPatterns("/shop/editAccount.do", "/shop/listOrders.do",
//					"/shop/viewOrder.do", "/shop/newOrder.do");		
//	}
	
	// /files/** 경로로 접근하면 C:/files/ 디렉토리에 저장된 파일을 서빙할 수 있게 됨.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/upload/**")
         .addResourceLocations("file:C:/absolute/path/to/upload/");
    }
}
