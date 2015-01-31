package biz.ezfolio.ws.stock;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.util.logging.Logger;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Retrieve stock prices from YAHOO Finance RSS feed.
 * 
 * @author lweeraratne
 *
 */
@Singleton
public class YahooTickerService implements TickerService {
	private static final String RESP_ITEMS_NODE = "items";
	private static final String RESP_VALUE_NODE = "value";

	private final static String YAHOO_RSS_PIPE_URL = "https://pipes.yahoo.com/pipes/pipe.run?"
			+ "_id=ZKJobpaj3BGZOew9G8evXg&_render=json&ticker=";

	@Inject HttpClient client;

	private Logger logger = Logger.getLogger(YahooTickerService.class.getName());

	private JsonParser parser = new JsonParser();
	private Gson gson = new Gson();

	@Override
	public List<StockItem> retriveStockPrices(String... symbols) throws IOException {
		checkNotNull(symbols);

		long start = System.currentTimeMillis();
		logger.info("Retrieving stock proces for " + symbols.length + " symbols.");
		HttpGet request = new HttpGet(buildUrl(symbols));
		HttpResponse response = client.execute(request);

		JsonObject respJsonObject = parser.parse(readResponse(response)).getAsJsonObject();
		JsonArray items = respJsonObject.get(RESP_VALUE_NODE)
				.getAsJsonObject().get(RESP_ITEMS_NODE).getAsJsonArray();

		List<StockItem> results = Lists.newArrayList();
		Iterator<JsonElement> it = items.iterator();

		while(it.hasNext()) {
			StockItem item = gson.fromJson(it.next(), StockItem.class);
			results.add(item);
		}

		logger.info("retrieved " 
				+ symbols.length + " symbols in " 
				+ (System.currentTimeMillis() - start) + " ms");

		return results;
	}

	private String readResponse(HttpResponse response) throws IOException {
		StringBuilder resp = new StringBuilder();
		BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));

		String line = "";
		while ((line = rd.readLine()) != null) {
			resp.append(line);
		}

		return resp.toString();
	}

	private String buildUrl(String... symbols) {
		StringBuilder params = new StringBuilder();
		for (String symbol : symbols) {
			params.append(symbol).append(",");
		}

		try {
			return YAHOO_RSS_PIPE_URL + URLEncoder.encode(params.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return params.toString();
		}
	}

}
