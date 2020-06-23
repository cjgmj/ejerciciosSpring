package com.cjgmj.form.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			LOG.info("Es un método del controlador: " + ((HandlerMethod) handler).getMethod().getName());
		}

		LOG.info("TiempoTranscurridoInterceptor: preHandle() empieza");
		LOG.info("Interceptando: " + handler);

		final Long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);

		final Random random = new Random();
		final Integer delay = random.nextInt(500);
		Thread.sleep(delay);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		final Long tiempoFin = System.currentTimeMillis();
		final Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		final Long tiempoTranscurrido = tiempoFin - tiempoInicio;

		if (modelAndView != null && handler instanceof HandlerMethod) {
			modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
		}

		LOG.info("Tiempo transcurrido: " + tiempoTranscurrido + " milisegundos");
		LOG.info("TiempoTranscurridoInterceptor: postHandle() acaba");
	}

}
