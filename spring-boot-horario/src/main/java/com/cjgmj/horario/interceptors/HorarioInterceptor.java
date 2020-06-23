package com.cjgmj.horario.interceptors;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("horarioInterceptor")
public class HorarioInterceptor implements HandlerInterceptor {

	@Value("${config.horario.apertura}")
	private Integer apertura;

	@Value("${config.horario.cierre}")
	private Integer cierre;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final Calendar calendar = Calendar.getInstance();
		final int hora = calendar.get(Calendar.HOUR_OF_DAY);

		if (hora >= this.apertura && hora < this.cierre) {
			final StringBuilder mensaje = new StringBuilder("Bienvenido a atención al cliente");
			mensaje.append(", el horario es desde las ");
			mensaje.append(this.apertura);
			mensaje.append(" horas");
			mensaje.append(" hasta las");
			mensaje.append(this.cierre);
			mensaje.append(" horas. Gracias por su visita.");

			request.setAttribute("mensaje", mensaje.toString());

			return true;
		}

		response.sendRedirect(request.getContextPath().concat("/cerrado"));

		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		final String mensaje = (String) request.getAttribute("mensaje");

		if (modelAndView != null && handler instanceof HandlerMethod) {
			modelAndView.addObject("horario", mensaje);
		}
	}

}
