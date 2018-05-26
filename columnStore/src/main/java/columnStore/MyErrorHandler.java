package columnStore;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class MyErrorHandler implements ErrorHandler{
public void warning(SAXParseException se){
System.err.println("warning : "+se.getMessage());
}
public void error(SAXParseException se){
System.err.println("error : "+se.getMessage());
}
public void fatalError(SAXParseException se){
System.err.println("fatal error : "+se.getMessage());
}
}
