

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CurrencyConverter
 */
@WebServlet("/convert")
public class CurrencyConverter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	// Conversion rates
    private static final Map<String, Double> conversionRates = new HashMap<>();

    static {
        conversionRates.put("USD_EUR", 0.91);
        conversionRates.put("EUR_USD", 1.10);
        conversionRates.put("USD_YUAN", 7.07);
        conversionRates.put("YUAN_USD", 0.14);
        conversionRates.put("EUR_YUAN", 7.74);
        conversionRates.put("YUAN_EUR", 0.13);
        // Add more currencies as needed
    }
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CurrencyConverter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// get the parameters
		String amountStr = request.getParameter("amount");
		String fromCurrency = request.getParameter("fromCurrency");
		String toCurrency = request.getParameter("toCurrency");

		PrintWriter out = response.getWriter();
		out.println("<html><body>");

		// Check if any of the parameters are null or empty
		if (amountStr == null || amountStr.isEmpty() || fromCurrency == null || fromCurrency.isEmpty() || toCurrency == null || toCurrency.isEmpty()) {
			out.println("<h2>Error: Please provide valid input for amount, fromCurrency, and toCurrency.</h2>");
		} else {
			try {
				// parse the parameters
				double amount = Double.parseDouble(amountStr);
				double convertedAmount = 0.0;
				boolean conversionSuccessful = false; // Flag to check if conversion was successful

				// USD -> EURO
				if ("USD".equalsIgnoreCase(fromCurrency) && "EUR".equalsIgnoreCase(toCurrency)) {
					convertedAmount =amount*0.91;
					conversionSuccessful = true; // Set flag to true

				} else if ("EUR".equalsIgnoreCase(fromCurrency) && "USD".equalsIgnoreCase(toCurrency)) {
					convertedAmount =amount * 1.10;
					conversionSuccessful = true; // Set flag to true
				} else if("EUR".equalsIgnoreCase(fromCurrency) && "EUR".equalsIgnoreCase(toCurrency)) {
					convertedAmount = amount;
					conversionSuccessful = true; // Set flag to true
				}else if("USD".equalsIgnoreCase(fromCurrency) && "USD".equalsIgnoreCase(toCurrency)) {
					convertedAmount = amount;
					conversionSuccessful = true; // Set flag to true

				}
				convertedAmount = Math.round(convertedAmount * 100.0) / 100.0;

				// Display the result if conversion was successful
				if (conversionSuccessful) {
					out.println("<h2>Currency Conversion Result</h2>");
					out.println("<p>" + amount + " " + fromCurrency + " = " + convertedAmount + " " + toCurrency + "</p>");
				} else {
					out.println("<h2>Error: Unsupported currency conversion. Currently, only USD to EUR and EUR to USD are supported.</h2>");
				}

			} catch (NumberFormatException e) {
				out.println("<h2>Error: Invalid amount. Please enter a valid number.</h2>");
			}
		}
		out.println("</body></html>");
	}
	
//	private boolean isInputValid()




}
