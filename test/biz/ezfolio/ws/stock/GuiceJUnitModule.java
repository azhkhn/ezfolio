package biz.ezfolio.ws.stock;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.inject.AbstractModule;

/**
 * Guice module for unit tests.
 * @author lweeraratne
 *
 */
public class GuiceJUnitModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TickerService.class).to(YahooTickerService.class);
		bind(HttpClient.class).to(DefaultHttpClient.class);
	}

}
