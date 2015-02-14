package biz.ezfolio.ws.guice;

import biz.ezfolio.ws.stock.TickerService;
import biz.ezfolio.ws.stock.SimpleTickerService;

import com.google.inject.AbstractModule;

/**
 * Guice module for unit tests.
 * @author lweeraratne
 *
 */
public class ApplicationGuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TickerService.class).to(SimpleTickerService.class);
	}
}
