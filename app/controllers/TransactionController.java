package controllers;

import models.CustomerAccount;
import models.Transaction;
import play.api.mvc.Call;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import views.html.transactions.history;
import views.html.transactions.layout;

import javax.inject.Inject;
import java.util.List;

import static views.html.transactions.layout.*;

public class TransactionController extends Controller {
    @Inject
    FormFactory formFactory;

    public Result history(){
        List<Transaction> transactions = Transaction.find.all();
        return ok(history.render(transactions));
    }

    public Result debitGet(){
        return operationGet(OK, routes.TransactionController.debitPost(),"Debit");
    }

    public Result debitPost(){
        Transaction.debit(getTransactionFieldFromForm());
        return redirect(routes.TransactionController.history());
    }

    public Result creditGet(){
         return operationGet(OK, routes.TransactionController.creditPost(),"Credit");
    }

    public Result creditPost(){
        try {
            Transaction.credit(getTransactionFieldFromForm());
        }catch (IllegalArgumentException e){
            flash("danger", "You don't have enough money. Please, reduce amount.");
            return operationGet(CONFLICT, routes.TransactionController.creditPost(),"Credit");
        }
        return redirect(routes.TransactionController.history());
    }

    public Result showTransactionDetails(int transactionId){
        return TODO;
    }

    private Transaction getTransactionFieldFromForm(){
        Form<Transaction> form = formFactory.form(Transaction.class).bindFromRequest();
        return form.get();
    }

    private Result operationGet(int results, Call call, String title){
        Form<Transaction> form = formFactory.form(Transaction.class);
        List<CustomerAccount> customerAccounts = CustomerAccount.find.all();
        return play.mvc.Results.status(results, layout.render(form, customerAccounts, call, title));
    }
}
