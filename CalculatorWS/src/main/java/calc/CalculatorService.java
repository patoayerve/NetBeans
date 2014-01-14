package calc;

import javax.annotation.Resource;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

/** Calculator Web Service
 *
 * @author Patricio Ayerve
 */

@WebService(serviceName="CalculatorService")
public class CalculatorService {
    // use @Resource injection to create a WebServiceContext for server logging
    private @Resource
    WebServiceContext webServiceContext;
    /** Calculates the sum of two numbers
     *
     * @param x integer number
     * @param y integer number
     * @return sum of two numbers
     * @throws calc.NegativeNumberException if one of two numbers is negative
     */
    @WebMethod(operationName = "suma")
    @WebResult( name="sumaResponse")
    public int suma(@WebParam(name = "x")int x, @WebParam(name = "y")int y)
        throws NegativeNumberException {

        getServletContext().log("Parameters: x="+x+", y="+y);
        if (x < 0) {
            throw new NegativeNumberException("x is less then zero");
        } else if (y < 0) {
            throw new NegativeNumberException("y is less then zero");
        } else {
            return x+y;
        }
    }
    
    @WebMethod(operationName = "resta")
    @WebResult( name="restaResponse")
    public int operation(@WebParam(name = "x") int x, @WebParam(name = "y") int y) 
            throws NegativeNumberException {
         getServletContext().log("Parameters: x="+x+", y="+y);
        if (x < 0) {
            throw new NegativeNumberException("x is less then zero");
        } else if (y < 0) {
            throw new NegativeNumberException("y is less then zero");
        } else {
            return x-y;
        }
    }
    
    private ServletContext getServletContext() {
        return (ServletContext) webServiceContext.getMessageContext().get(
                MessageContext.SERVLET_CONTEXT);
    }
}
