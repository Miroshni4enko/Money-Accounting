package controllers;


import model.Transaction;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.transactions.debit;
import views.html.transactions.history;

import javax.inject.Inject;
import java.util.Set;

public class TransactionController extends Controller {
    @Inject
    FormFactory formFactory;

    public Result history(){
        Set<Transaction> transactions = Transaction.getTransactionHistory();

        return ok(history.render(transactions));
    }

    public Result debitGet(){
        Form<Transaction> debitForm = formFactory.form(Transaction.class);

        return ok(debit.render(debitForm));
    }

    public Result debitPost(){
        Form<Transaction> debitForm = formFactory.form(Transaction.class).bindFromRequest();
        Transaction transaction = debitForm.get();
        System.out.println(" am ="+ transaction.getAmount() + "; " + transaction.getFromCustomer());
        Transaction.debit(transaction);
        return redirect(routes.TransactionController.history());
    }

    public Result creditGet(){
        return TODO;
    }

    public Result creditPost(){
        return TODO;
    }

    public Result showTransactionDetails(int transactionId){
        return TODO;
    }
}
