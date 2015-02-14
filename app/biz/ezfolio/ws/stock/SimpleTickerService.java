package biz.ezfolio.ws.stock;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.inject.Singleton;

/**
 * Retrieve stock prices from YAHOO Finance RSS feed.
 * 
 * @author lweeraratne
 *
 */
@Singleton
public class SimpleTickerService implements TickerService {
	private static final long REQUEST_TIMEOUT = 3000l;

	private final static String WS_URL = "http://dev.markitondemand.com/Api/v2/Quote/json?symbol=";

	private Gson gson = new Gson();

	private static WSClient client = play.libs.ws.WS.client();

	@Override
	public List<StockItem> retriveStockPrices(String... symbols) throws IOException {
		checkNotNull(symbols);

		long start = System.currentTimeMillis();

		Map<String, Promise<JsonNode>> symbolToPromiseMap = Maps.newHashMap();
		List<StockItem> results = Lists.newArrayList();

		for (String symbol : symbols) {
			Promise<WSResponse> promise = client.url(buildUrl(symbol))
					.setHeader("Cache-Control", "no-cache").get();

			Promise<JsonNode> jsonParsedResponse = promise.map(new Function<WSResponse, JsonNode>() {
				@Override
				public JsonNode apply(WSResponse response) throws Throwable {
					return response.asJson();
				}
			});
			symbolToPromiseMap.put(symbol, jsonParsedResponse);
		}

		for (String symbol : symbolToPromiseMap.keySet()) {
			Promise<JsonNode> promise = symbolToPromiseMap.get(symbol);
			JsonNode node = promise.get(REQUEST_TIMEOUT);

			results.add(gson.fromJson(node.toString(), StockItem.class));
		}

		play.Logger.info(String.format("Retrieved %d symbols in %d ms",
				symbols.length, (System.currentTimeMillis() - start)));
		return results;
	}

	private String buildUrl(String symbol) {
		return WS_URL + symbol;
	}

}
