package pl.readstack.client.authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.readstack.domain.api.UserRegistration;
import pl.readstack.domain.api.UserService;

import java.io.IOException;
@WebServlet("/login")
@ServletSecurity(httpMethodConstraints = @HttpMethodConstraint(value = "GET", rolesAllowed="USER"))
public class LoginController extends HttpServlet{

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect(request.getContextPath());
    }

}