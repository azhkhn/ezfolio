package biz.ezfolio.ws.stock;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Unit test for {@link SimpleTickerService}
 * 
 * @author lweeraratne
 *
 */
public class YahooTickerServiceTest {
	private TickerService tickerService;
	private Injector injector;

	@Before
	public void setup() {
		injector = Guice.createInjector(new GuiceJUnitModule());
		tickerService = injector.getInstance(TickerService.class);
	}

	@After
    public void tearDown() throws Exception {
        injector = null;
    }

	@Test
	public void testRetriveSingleStock() throws IOException {
		String [] symbols = new String[] {"LC", "GOOG", "APPL"};
		List<StockItem> items = tickerService.retriveStockPrices(symbols);

		assertEquals(3, items.size());
	}
}
