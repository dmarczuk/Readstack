package pl.readstack.client.vote;

import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.readstack.domain.api.DiscoveryService;
import pl.readstack.domain.api.DiscoveryVote;
import pl.readstack.domain.api.DiscoveryVoteService;

import java.io.IOException;
import java.util.Enumeration;
import java.util.*;

@WebServlet("/discovery/vote")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = "USER")
        }
)
public class DiscoveryVoteController extends HttpServlet {
    private DiscoveryVoteService voteService = new DiscoveryVoteService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DiscoveryVote discoveryVote = createDiscoveryVote(request);
        voteService.addVote(discoveryVote);
        String categoryParameter = request.getParameter("idCat");
        if (categoryParameter != "") {
            response.sendRedirect(request.getContextPath() + "/category?id=" + categoryParameter);
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    private DiscoveryVote createDiscoveryVote(HttpServletRequest request) {
        Integer discoveryId = Integer.valueOf(request.getParameter("id"));
        String voteType = request.getParameter("type");
        String username = request.getUserPrincipal().getName();
        DiscoveryVote discoveryVote = new DiscoveryVote(username, discoveryId, voteType);
        return discoveryVote;
    }
}
