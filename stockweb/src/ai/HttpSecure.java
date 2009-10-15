package ai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author: Humberto Rocha Loureiro (humbertorocha@gmail.com)
 * @modify: 
 */

public class HttpSecure implements javax.net.ssl.X509TrustManager {

	//LA COSTANTE DEL TIMEOUT DI CONNESSIONE PER LE RICHIESTE HTTP IN SECONDI
	private static int NUM_SECONDS_FOR_TIMEOUT=180;

	//IL THREAD PER LA RICHIESTA HTTP CON TIMEOUT
	private class RunnableImpl implements Runnable {

		private URL endpoint;
		private String result=new String("");
	
		public RunnableImpl(URL endpoint) {
			this.endpoint=endpoint;
		}
		
		public void run() {
			try {
				URLConnection yc = endpoint.openConnection();
			 	InputStreamReader inputStreamReader = new InputStreamReader(yc.getInputStream());
				BufferedReader in = new BufferedReader(inputStreamReader);
		        String inputLine;
		        while ((inputLine = in.readLine()) != null){
		            result += inputLine + "|";
		        } 
		        in.close();
		        inputStreamReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}
		
	} 

	//QUESTO METODO FA UNA RICHIESTA HTTP ALL'END-POINT INDICATO, STABILENDO UN TIMEOUT DI CONNESSIONE. lA RETE SISAL NON FORNISCE UN TIMEOUT
	//DI CONNESSIONE PER TUTTE LE CONNESSIONI HTTP NEL SENSO CHE RIMANGONO ATTIVE ALL'INFINITO: QUESTA IMPLEMENTAZIONE RISOLVE IL PROBLEMA
	//DANDO UN TIMEOUT APPLICATIVO
	public String getContentHttpsss(String url) throws Exception {
		String result="";

		URL endPoint = new URL(url);
	 	//URLConnection yc = endPoint.openConnection();
		ExecutorService executorService = Executors.newCachedThreadPool();
		Runnable thread=new RunnableImpl(endPoint);
		Future future = executorService.submit(thread);
		future.get(NUM_SECONDS_FOR_TIMEOUT, TimeUnit.SECONDS);
		result=((RunnableImpl)thread).getResult();
		//log.debug("HTTP request to URL '"+url+ "' responded \n"+result);

        return result;
	}
	
	// TrustManager Methods
	public void checkClientTrusted(X509Certificate[] chain, String authType) {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}