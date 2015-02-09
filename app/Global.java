import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import play.Application;
import play.GlobalSettings;
import play.Logger;
import biz.ezfolio.ws.guice.ApplicationGuiceModule;
import biz.ezfolio.ws.stock.StockItem;
import biz.ezfolio.ws.stock.TickerService;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Play framework global settings
 * @author lweeraratne
 *
 */
public class Global extends GlobalSettings {

	@Override
	public void onStart(Application arg0) {
		play.Logger.warn("Starting global settings application");

		Injector injector = Guice.createInjector(new ApplicationGuiceModule());
		final TickerService tickerService = injector.getInstance(TickerService.class);
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
		executor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					List<StockItem> stockPrices = tickerService.retriveStockPrices("GOOG", "LC", "AAPL");

					for (StockItem price : stockPrices) {
						Logger.info(price.getSymbol() + " - " + price.getLast());
					}
					Logger.info("===================================");
				} catch (IOException e) {

				}
			}
		}, 0, 5, TimeUnit.SECONDS);
	}

	@Override
	public void onStop(Application arg0) {
		play.Logger.warn("Stopping application.");
	}

}
