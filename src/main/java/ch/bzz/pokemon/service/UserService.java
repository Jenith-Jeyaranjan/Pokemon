package ch.bzz.pokemon.service;

import ch.bzz.pokemon.data.UserData;
import ch.bzz.pokemon.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.awt.*;

/**
 * services for authentication and authorization of users
 */

@Path("user")
public class UserService {

    /**
     * login service
     * @param username the username of the user to be logged in
     * @param passwort the password of the user to be logged in
     * @return 200 if the user was logged in, 404 if the user was not found or the password was wrong
     */
    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(
            @FormParam("username") String username,
            @FormParam("passwort") String passwort
    ){
        int httpStatus;
        User user = UserData.findUser(username,passwort);
        System.out.println(user);
        if (user == null || user.getRole() == null || user.getRole().equals("guest")){
            httpStatus = 404;
        }else {
            httpStatus = 200;
        }
        NewCookie cookie = new NewCookie(
                "userRole",
                user.getRole(),
                "/",
                "",
                "Login-Cookie",
                600,
                false


        );
        Response response = Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }

    /**
     * logout service
     * @return 200
     */
    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout(){
        NewCookie cookie = new NewCookie("userRole", "","/", null, null, 0, false);
        Response response = Response
                .status(200)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }

}
