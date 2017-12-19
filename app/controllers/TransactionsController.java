package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.CustomerAccount;
import models.Transaction;
import play.api.mvc.Call;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;
import views.html.transactions.history;
import views.html.transactions.layout;

import javax.inject.Inject;
import java.util.List;

import static views.html.transactions.layout.*;


public class TransactionsController extends Controller {
    @Inject
    FormFactory formFactory;
    
    public Result history(){
        return ok(Json.toJson(Transaction.findAllTransactionsOrderByDate()));
    }

    public Result showByCustomerAccountId(Long id){
        return ok(Json.toJson(Transaction.findTransactionsByAccountId(id)));
    }

    public Result debit(){
        Transaction.debit(getTransactionFieldFromForm());
        return ok(buildJsonResponse("success", "Debit was successfully"));
    }

    public Result credit(){
        try {
            Transaction.credit(getTransactionFieldFromForm());
        }catch (IllegalArgumentException e){
            notAcceptable(buildJsonResponse("error", "You don't have enough money. Please, reduce amount."));
        }
        return ok(buildJsonResponse("success", "Credit was successfully"));
    }

    private Transaction getTransactionFieldFromForm(){
        Form<Transaction> form = formFactory.form(Transaction.class).bindFromRequest();
        return form.get();
    }

    public static ObjectNode buildJsonResponse(String type, String message) {
        ObjectNode wrapper = Json.newObject();
        ObjectNode msg = Json.newObject();
        msg.put("message", message);
        wrapper.set(type, msg);
        return wrapper;
    }

    public Result delete(Long id){
        Transaction transaction = Transaction.find.byId(id);
        if (transaction == null) {
            return notFound(buildJsonResponse("error", "Transaction didn't find."));
        }
        transaction.delete();
        return ok(buildJsonResponse("success", "Transaction deleted successfully"));
    }

}
