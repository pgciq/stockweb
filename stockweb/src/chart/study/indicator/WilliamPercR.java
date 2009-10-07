package chart.study.indicator;

import java.util.*;

import chart.study.PriceBar;
import chart.study.QuoteHistory;

/**
 * Exponential Moving Average.
 */
public class WilliamPercR extends Indicator {
    private final int length;
    private int lastPreview = 1;

    public WilliamPercR(QuoteHistory qh, int length) {
        super(qh);
        this.length = length;
    }

    public WilliamPercR(QuoteHistory qh, int length, String action, int level) {
        super(qh);
        this.length = length;
        if((action.equals("preview")) || (action.equals("search"))){
        	this.lastPreview = 1+level;
        }
    }

    @Override
    public double calculate() {
        List<PriceBar> priceBars = qh.getAll();
        int lastBar = priceBars.size()-(lastPreview+1);
        
        double lastMax = priceBars.get(lastBar).getHigh();
        double lastMin = priceBars.get(lastBar).getLow();

        double priceMaxTmp = 0;
        double priceMaxLast = 0;
        double priceMax = 0;
        
        double priceMinTmp = 0;
        double priceMinLast = 0;
        double priceMin = 1000;

        double priceClose = 0;
        
        long lastDate = 0L;
        
        for(int i=(lastBar - length)+1; i<=lastBar; i++){
        	lastDate = priceBars.get(i).getDate();
        	priceClose = priceBars.get(i).getClose();
        	
        	priceMaxLast = priceMax; 
        	priceMinLast = priceMin; 

        	priceMaxTmp = priceBars.get(i).getHigh();
        	priceMinTmp = priceBars.get(i).getLow();

        	if(priceMaxTmp > priceMaxLast)
        		priceMax = priceMaxTmp;

        	if(priceMinTmp < priceMinLast)
        		priceMin = priceMinTmp;
        }
        
       double percR = ((priceClose - priceMax) / (priceMax - priceMin)) * -100;
       
       //System.out.println("@percR : " + percR);

/*  %R = (Fechúltimo - Hn) ÷ (Hn - Ln) x 100 

Onde:

    * Fechúltimo = Último preço de fechamento;
    * n = Número de períodos;
    * Ln = preço de venda mais baixo dos últimos n períodos;
    * Hn = preço de compra mais alto dos últimos n períodos.  
 */        
        
        
        
/*    	System.out.println("@Date: " + lastDate);
  
        
        System.out.println("\nlastMax: " + lastMax);
        System.out.println("lastMin: " + lastMin + "\n");

*/        
        return percR;
    }
    
}
