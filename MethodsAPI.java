package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import util.Uppgift1.Uppgift1;
import util.Uppgift1.User;
import util.Uppgift2.Uppgift2;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("methods")
public class MethodsAPI {


    @GET
    @Path("userswithreversednames")
    public static Response usersWithReversedNames() throws IOException {
        Uppgift1 uppgift1 = new Uppgift1();
        List<String> reversedNames = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (User user : uppgift1.getUsers()) {
            reversedNames.add(uppgift1.reverseString(user.getFirstname()));
        }
        String jsonString = objectMapper.writeValueAsString(reversedNames);
        return Response.status(Response.Status.OK).entity(jsonString).build();
    }

    /*
    Fick det här felmeddelandet:
    The @FormParam is utilized when the content type of the request entity is not application/x-www-form-urlencoded
    Därav "@Consumes(MediaType.APPLICATION_FORM_URLENCODED)"
    */

    @POST
    @Path("reversestring")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public static Response reverseString(@FormParam("string") String string) {
        Uppgift1 uppgift1 = new Uppgift1();
        return Response.status(Response.Status.OK).entity(uppgift1.reverseString(string)).build();
    }

    @POST
    @Path("ispalindrome")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public static Response isPalindrome(@FormParam("string") String string) {
        String responseString;
        Uppgift1 uppgift1 = new Uppgift1();
        if (uppgift1.isPalindrome(string)) {
            responseString = string + " is a palindrome!";
        } else {
            responseString = string + " is not a palindrome!";
        }
        return Response.status(Response.Status.OK).entity(responseString).build();

    }

    @POST
    @Path("padnumberwithzeroes")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public static Response padNumberWithZeroes(@FormParam("number") int number) {
        Uppgift2 uppgift2 = new Uppgift2();
        String responsePadNumberWithZeros = uppgift2.padNumberWithZeroes(number);
        return Response.status(Response.Status.OK).entity(responsePadNumberWithZeros).build();
    }

    @POST
    @Path("findnthlargestnumber")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public static Response findNthLargestNumber(@FormParam("numbers") List<Integer> numbers,
                                                @FormParam("nthlargestnumber") int nthLargestNumber) {
        Uppgift2 uppgift2 = new Uppgift2();
        return Response.status(Response.Status.OK).entity(uppgift2.findNthLargestNumber(numbers, nthLargestNumber)).build();

    }

}
