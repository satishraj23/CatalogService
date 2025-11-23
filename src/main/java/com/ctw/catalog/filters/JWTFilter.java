package com.ctw.catalog.filters;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JWTFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpReq = (HttpServletRequest) request;

		String token = extractTokenFromCookie(httpReq);

		Claims claims = validateToken(token);
		
		String username = claims.getSubject();
		List<String> roles = claims.get("ROLE", List.class);
		
		List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,null, authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);

	}

	private String extractTokenFromCookie(HttpServletRequest request) {

		Cookie[] cookies = request.getCookies();

		if (cookies == null)
			return null;

		for (Cookie c : cookies) {
			if ("access_token".equals(c.getName())) {
				return c.getValue();
			}

		}
		return null;

	}

	private Claims validateToken(String token) {

		PublicKey publicKey = loadPublicKey();
		
		System.out.println("inside validateToken publicKey:: " + publicKey);
		try {
			return Jwts
	                .parser()              // 🔥 MUST use parserBuilder()
	                .verifyWith(publicKey)      // RSA Public Key
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();

	    } catch (ExpiredJwtException e) {
	        throw new RuntimeException("Token expired");
	    } catch (JwtException e) {
	        throw new RuntimeException("Token invalid");
	    }

	}

	private PublicKey loadPublicKey() {

		try {

			ClassPathResource resource = new ClassPathResource("keys/public.pem");

			String key = new String(resource.getInputStream().readAllBytes());
			
			 key = key
		                .replace("-----BEGIN PUBLIC KEY-----", "")
		                .replace("-----END PUBLIC KEY-----", "")
		                .replaceAll("\\s", "");
			 

			byte[] decoded = Base64.getDecoder().decode(key);

			X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);

			KeyFactory keyFactory = null;

			try {
				keyFactory = KeyFactory.getInstance("RSA");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			return keyFactory.generatePublic(spec);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
