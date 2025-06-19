package QuizApp.backend.util;

import java.io.IOException;

import org.apache.catalina.connector.Response;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import QuizApp.backend.services.CustomUserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserService customUserService;

    @Override
    protected void doFilterInternal(
        HttpServletRequest req,
        HttpServletResponse res,
        FilterChain chain
    ) throws ServletException, IOException{
        
        final String authHeader = req.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            chain.doFilter(req, res);
            return;
        }
        String token = authHeader.substring(7);
        try {
            if(!jwtTokenUtil.validateToken(token)){
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid JWT Token!");
            }

            String username = jwtTokenUtil.extractUsername(token);
            UserDetails userDetails = customUserService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
        SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (ExpiredJwtException e) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED,"JWT Expired!");
            return;
        }catch(MalformedJwtException |SignatureException |IllegalArgumentException e){
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token!");
            return;
        }catch(JwtException e){
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Error!");
            return;
        }
        chain.doFilter(req, res);
        

    }
}
