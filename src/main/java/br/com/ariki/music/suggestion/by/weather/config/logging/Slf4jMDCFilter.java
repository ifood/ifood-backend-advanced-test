package br.com.ariki.music.suggestion.by.weather.config.logging;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Slf4jMDCFilter extends OncePerRequestFilter {

	private String responseHeader;
	private String mdcTokenKey;
	private String requestHeader;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		try {
			
			final String token;
			
			if (!StringUtils.isEmpty(requestHeader) && !StringUtils.isEmpty(request.getHeader(requestHeader))) {
				token = request.getHeader(requestHeader);
			
			} else {
			
				token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
			}
			
			MDC.put(mdcTokenKey, token);
			
			if (!StringUtils.isEmpty(responseHeader)) {
				response.addHeader(responseHeader, token);
			}
			
			filterChain.doFilter(request, response);
			
		} finally {
			MDC.remove(mdcTokenKey);
		}

	}
}
