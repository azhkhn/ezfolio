package biz.ezfolio.ws.stock;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import com.google.inject.Inject;

/**
 * Retrieve stock prices from YAHOO Finance RSS feed.
 * 
 * @author lweeraratne
 *
 */
public class YahooTickerService implements TickerService {
	private final static String YAHOO_RSS_PIPE_URL = "https://pipes.yahoo.com/pipes/pipe.run? "
			+ "_id=ZKJobpaj3BGZOew9G8evXg&_render=json&ticker=";

	@Inject HttpClient client;

	@Override
	public List<StockItem> retriveStockPrices(String... symbols) throws IOException {
		checkNotNull(symbols);

		HttpGet request = new HttpGet(buildUrl(symbols));
		HttpResponse response = client.execute(request);

		String lineAll = new String();
		BufferedReader rd = new BufferedReader
				  (new InputStreamReader(response.getEntity().getContent()));
				    
				String line = "";
				while ((line = rd.readLine()) != null) {
					lineAll+= line;
				}

		return null;
	}

	private String buildUrl(String... symbols) {
		StringBuilder params = new StringBuilder();
		for (String symbol : symbols) {
			params.append(symbol).append(",");
		}

		try {
			return URLEncoder.encode(YAHOO_RSS_PIPE_URL + params.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return params.toString();
		}
	}

}
