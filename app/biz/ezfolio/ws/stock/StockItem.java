package biz.ezfolio.ws.stock;

import java.io.Serializable;

/**
 * Details for a given stock symbol at a given point in time.
 * 
 * @author lweeraratne
 *
 */
public class StockItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private String Name;
	private float LastPrice;
	private String Symbol;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public float getLastPrice() {
		return LastPrice;
	}

	public void setLastPrice(float lastPrice) {
		this.LastPrice = lastPrice;
	}

	public String getSymbol() {
		return Symbol;
	}

	public void setSymbol(String symbol) {
		Symbol = symbol;
	}

}
