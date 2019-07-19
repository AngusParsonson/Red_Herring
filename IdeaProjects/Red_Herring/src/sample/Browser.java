package sample;

import javafx.scene.web.WebView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Browser {

    public WebView browse_url(String url) {
        WebView webView = new WebView();

        URL url_local = null;
        try {
            url_local = new File("Sites/paypal/paypal.html").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        webView.getEngine().load(url_local.toExternalForm());

        return webView;
    }
}
