package biz.ezfolio.ws.stock.akka;

import akka.actor.UntypedActor;


/**
 * Each Stock symbol is represented by a {@link StockItemActor}. This contains a list
 * of watchers for this symbol and updates them when it is updated.
 * 
 * @author lweeraratne
 *
 */
public class StockItemActor extends UntypedActor{

	@Override
	public void onReceive(Object message) {
		
	}
}
