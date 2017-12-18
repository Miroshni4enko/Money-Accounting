package controllers;


import model.CustomerAccount;
import model.Transaction;
import play.data.Form;
import play.data.FormFactory;
import play.data.format.Formatters;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.transactions.debit;
import views.html.transactions.history;

import javax.inject.Inject;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

public class TransactionController extends Controller {
    @Inject
    FormFactory formFactory;

    public Result history(){
        List<Transaction> transactions = Transaction.find.all();
        return ok(history.render(transactions));
    }

    public Result debitGet(){
        Form<Transaction> debitForm = formFactory.form(Transaction.class);
        List<CustomerAccount> customerAccounts = CustomerAccount.find.all();
        return ok(debit.render(debitForm, customerAccounts));
    }

    public Result debitPost(){
        Form<Transaction> debitForm = formFactory.form(Transaction.class).bindFromRequest();
        Transaction transaction = debitForm.get();
        System.out.println(" am ="+ transaction.amount + "; " + transaction.description);
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
