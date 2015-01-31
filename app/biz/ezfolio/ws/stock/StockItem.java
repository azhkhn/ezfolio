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

	private final String ticker;
	private final float price;
	private final String time;

	public StockItem(String symbol, float price, String time) {
		super();
		this.ticker = symbol;
		this.price = price;
		this.time = time;
	}

	public String getSymbol() {
		return ticker;
	}

	public float getPrice() {
		return price;
	}

	public String getTime() {
		return time;
	}
}
