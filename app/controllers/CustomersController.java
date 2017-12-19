package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Customer;
import models.CustomerAccount;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import static controllers.TransactionsController.buildJsonResponse;

public class CustomersController extends Controller {

    public Result list(){
        return ok(Json.toJson(Customer.find.all()));
    }

    public Result show(Long id){
        Customer customer = Customer.find.byId(id);
        if(customer == null) {
            return notFound(buildJsonResponse("error", "Customer didn't find."));
        }
        return ok(Json.toJson(customer));
    }

    public Result update(){
        JsonNode json = request().body().asJson();
        Customer customer = Json.fromJson(json, Customer.class);
        Customer oldCustomer = Customer.find.byId(customer.customerId);
        if(oldCustomer == null) {
            return notFound(buildJsonResponse("error", "Customer didn't find."));
        }
        oldCustomer.address = customer.address;
        oldCustomer.name = customer.name;
        oldCustomer.phone = customer.phone;
        oldCustomer.update();
        return ok(buildJsonResponse("success", "Customer updated successfully"));
    }

    public Result create(){
        JsonNode json = request().body().asJson();
        Customer customer = Json.fromJson(json, Customer.class);
        customer.save();
        return ok(buildJsonResponse("success", "Customer created successfully"));
    }

    public Result delete(Long id){
        Customer customer = Customer.find.byId(id);
        if (customer == null) {
            return notFound(buildJsonResponse("error", "Account didn't find."));
        }
        customer.delete();
        return ok(buildJsonResponse("success", "Account deleted successfully"));
    }

}
