package biz.ezfolio.ws.stock;

import java.io.Serializable;

/**
 * Details for a given stock symbol  at a given point in time.
 * 
 * @author lweeraratne
 *
 */
public class StockItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private final String symbol;
	private final String price;
	private final long time;

	public StockItem(String symbol, String price, long time) {
		super();
		this.symbol = symbol;
		this.price = price;
		this.time = time;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getPrice() {
		return price;
	}

	public long getTime() {
		return time;
	}
}
