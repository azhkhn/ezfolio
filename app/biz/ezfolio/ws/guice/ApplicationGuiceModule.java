package biz.ezfolio.ws.guice;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import biz.ezfolio.ws.stock.TickerService;
import biz.ezfolio.ws.stock.YahooTickerService;

import com.google.inject.AbstractModule;

/**
 * Guice module for unit tests.
 * @author lweeraratne
 *
 */
public class ApplicationGuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TickerService.class).to(YahooTickerService.class);
		bind(HttpClient.class).to(DefaultHttpClient.class);
	}
}
