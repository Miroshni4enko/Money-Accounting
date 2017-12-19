package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Customer;
import models.CustomerAccount;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import static controllers.TransactionsController.buildJsonResponse;


public class AccountsController extends Controller {


    public Result list(){
        return ok(Json.toJson(CustomerAccount.find.all()));
    }

    public Result show(Long id) {
        CustomerAccount customerAccount = CustomerAccount.find.byId(id);
        if (customerAccount == null) {
            return notFound(buildJsonResponse("error", "Account didn't find."));
        }
        return ok(Json.toJson(customerAccount));
    }

    public Result update(){
        JsonNode json = request().body().asJson();
        CustomerAccount customerAccount = Json.fromJson(json, CustomerAccount.class);
        CustomerAccount oldCustomerAccount = CustomerAccount.find.byId(customerAccount.accountNumber);
        if (oldCustomerAccount == null) {
            return notFound(buildJsonResponse("error", "Account didn't find."));
        }
        oldCustomerAccount.balance = customerAccount.balance;
        oldCustomerAccount.description = customerAccount.description;
        oldCustomerAccount.accountNumber = customerAccount.accountNumber;
        oldCustomerAccount.update();
        return ok(buildJsonResponse("success", "Account updated successfully"));
    }

    public Result create(){
        JsonNode json = request().body().asJson();
        CustomerAccount customerAccount = Json.fromJson(json, CustomerAccount.class);
        customerAccount.save();
        return ok(buildJsonResponse("success", "Account created successfully"));
    }

    public Result delete(Long id){
        CustomerAccount customerAccount = CustomerAccount.find.byId(id);
        if (customerAccount == null) {
            return notFound(buildJsonResponse("error", "Account didn't find."));
        }
        customerAccount.delete();
        return ok(buildJsonResponse("success", "Account deleted successfully"));
    }
}
