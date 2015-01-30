package biz.ezfolio.ws.stock;

import java.io.IOException;
import java.util.List;

/**
 * This service connects to stock price provider service and retrieves them.
 * @author lweeraratne
 *
 */
public interface TickerService {

	/**
	 * Retrieve stock prices for the given list of @code symbols
	 * 
	 * @param symbols list of symbols to retrieve from stock details for.
	 * @return
	 * @throws IOException if network failed. OK to retry
	 */
	public List<StockItem> retriveStockPrices(String ... symbols) 
			throws IOException;
}
