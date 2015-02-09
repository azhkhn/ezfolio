package controllers;

import java.io.IOException;
import java.util.List;

import biz.ezfolio.ws.guice.ApplicationGuiceModule;
import biz.ezfolio.ws.stock.StockItem;
import biz.ezfolio.ws.stock.TickerService;

import com.google.inject.Guice;
import com.google.inject.Injector;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * This is a test class. Will be used to implement the web application later on.
 * @author lweeraratne
 *
 */
public class GetSymbol extends Controller {
	static Injector injector = Guice.createInjector(new ApplicationGuiceModule());
	public static Result start(String symbol) throws IOException {
		TickerService tickerService = injector.getInstance(TickerService.class);

		List<StockItem> items = tickerService.retriveStockPrices(symbol);

		if (items == null || items.size() < 1) {
			return badRequest();
		}

        return ok(views.html.symbol.render(items.get(0)));
    }
}
