package api.app.controller;

import api.app.model.entity.user.User;
import api.app.model.entity.user.UserDTO;
import api.app.service.user.UserService;
import api.app.service.user.UserServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final int JWT_TOKEN_TIME_MINUTES = 360;

	@Autowired
	private UserServiceImpl UserService;

	@Autowired
	private SecretKey secretKey;

	@PostMapping("/login")
	public ResponseEntity<?> login (@RequestBody UserDTO credentials){
		User usuario = UserService.authenticate(credentials.getUsername(), credentials.getPassword());
		System.out.println("Received UserDTO: " + credentials);
		if(usuario != null) {

			// Crea el token JWT
			String tokenJWT = Jwts.builder().
					setSubject(credentials.getUsername())
					.setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_TIME_MINUTES * 60 * 1000 )) // 6 horas
					.signWith(secretKey, SignatureAlgorithm.HS256).compact();

			// Retorna el token
			return new ResponseEntity<>(tokenJWT , HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<>("Credenciales Invalidas",HttpStatus.UNAUTHORIZED);
		}
	}
}
