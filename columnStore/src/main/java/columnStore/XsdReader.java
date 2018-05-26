package columnStore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.xml.sax.SAXException;

import com.sun.xml.xsom.XSComplexType;
import com.sun.xml.xsom.XSContentType;
import com.sun.xml.xsom.XSModelGroup;
import com.sun.xml.xsom.XSParticle;
import com.sun.xml.xsom.XSSchema;
import com.sun.xml.xsom.XSSchemaSet;
import com.sun.xml.xsom.XSTerm;
import com.sun.xml.xsom.parser.XSOMParser;

public class XsdReader {

	static final LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
	// static final LinkedHashMap<String, String> newHashMap = new
	// LinkedHashMap<String, String>();
	static final ArrayList<String> tablenames = new ArrayList<String>();

	public static ArrayList<String> gettablenames() {
		return tablenames;
	}

	public LinkedHashMap<String, String> parseSchema(String xsdFile) {
		// TODO Auto-generated method stub

		XSOMParser parser = new XSOMParser();

		parser.setErrorHandler(new MyErrorHandler());
		try {
			File file = new File(xsdFile);
			parser.parse(file);
			XSSchemaSet schemaSet = parser.getResult();
			XSSchema xsSchema = schemaSet.getSchema(1);
			// Iterator<XSSchema> schemaIter = schemaSet.iterateSchema();
			// while (schemaIter.hasNext()) {

			// XSSchema xsSchema = (XSSchema) schemaIter.next();
			// System.out.println("at line 41");
			Iterator<XSComplexType> i = xsSchema.iterateComplexTypes();
			// System.out.println(i);
			while (i.hasNext()) {
				// System.out.println(i.next());
				XSComplexType c = i.next();
				XSContentType xsContentType = c.getContentType();
				XSParticle particle = xsContentType.asParticle();
				if (particle != null) {
					XSTerm term = particle.getTerm();
					if (term.isModelGroup()) {
						XSModelGroup xsModelGroup = term.asModelGroup();
						XSParticle[] particles = xsModelGroup.getChildren();
						String propertyName = null;
						String propertyType = null;
						for (XSParticle p : particles) {
							XSTerm pterm = p.getTerm();

							if (pterm.isElementDecl()) { // xs:element inside complex type
								// System. out.println(pterm);
								propertyName = pterm.asElementDecl().getName();
								propertyType = pterm.asElementDecl().getType().getName();
								// System.out.println(propertyName + " is a " + propertyType);
								String type = null;
								if (propertyType.equals("float"))
									type = "float";
								if (propertyType.equals("string"))
									type = "varchar(255)";
								if (propertyType.equals("int"))
									type = "integer";
								if (propertyType.equals("date"))
									type = "date";

								hashMap.put(propertyName, type);
								tablenames.add(propertyName);
							}
						}
					}
				}

			}
			// }

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		}

		return hashMap;
		// return null;
	}

}