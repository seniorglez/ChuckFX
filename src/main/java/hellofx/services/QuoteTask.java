package hellofx.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import hellofx.model.Quote;
import javafx.concurrent.Task;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class QuoteTask extends Task<String> {
    private String restURL;



    @Override
    protected String call() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Quote quote = objectMapper.readValue(new URL(restURL),Quote.class);
        String txt = getQuoteText(quote);
        updateValue(txt);
        return txt;
    }

    private String getQuoteText(Quote quote) throws MalformedURLException, URISyntaxException {
        return quote.getValue();
    }

    @Override
    protected void succeeded() {
        System.out.println("Quote Task succeeded!");
        super.succeeded();
    }

    public void setRestURL(String restURL) {
        this.restURL = restURL;
    }
}
