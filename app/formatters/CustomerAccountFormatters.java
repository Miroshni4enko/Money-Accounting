package formatters;

import model.CustomerAccount;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import java.time.LocalTime;

import play.data.format.Formatters;
import play.data.format.Formatters.SimpleFormatter;
import play.i18n.MessagesApi;

@Singleton
public class CustomerAccountFormatters implements Provider<Formatters> {

    private final MessagesApi messagesApi;

    @Inject
    public CustomerAccountFormatters(MessagesApi messagesApi) {
        this.messagesApi = messagesApi;
    }

    @Override
    public Formatters get() {
        Formatters formatters = new Formatters(messagesApi);

        formatters.register(CustomerAccount.class, new SimpleFormatter<CustomerAccount>() {

            @Override
            public CustomerAccount parse(String input, Locale arg1) throws ParseException {
                CustomerAccount customerAccount = CustomerAccount.find.byId(new Long(input));
                return customerAccount;
            }

            @Override
            public String print(CustomerAccount customerAccount, Locale arg1) {
                return customerAccount.accountNumber.toString();
            }

        });
        return formatters;
    }

}
