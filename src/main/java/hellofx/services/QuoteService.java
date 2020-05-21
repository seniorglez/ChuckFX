package hellofx.services;

import hellofx.model.Quote;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class QuoteService extends Service<String> {

    private String restURL = "https://api.chucknorris.io/jokes/random";
    @Override
    protected Task<String> createTask() {
        QuoteTask qt = new QuoteTask();
        qt.setRestURL(restURL);
        return  qt;
    }


}
