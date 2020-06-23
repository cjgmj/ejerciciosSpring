package com.cjgmj.form.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOG.info("TiempoTranscurridoInterceptor: preHandle()");

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
		LOG.info("TiempoTranscurridoInterceptor: postHandle()");

		final Long tiempoFin = System.currentTimeMillis();
		final Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		final Long tiempoTranscurrido = tiempoFin - tiempoInicio;

		if (modelAndView != null) {
			modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
		}

		LOG.info("Tiempo transcurrido: " + tiempoTranscurrido + " milisegundos");
	}

}
