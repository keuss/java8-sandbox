package com.cgi.sandbox.xml;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLOutputFactory;

import javax.xml.stream.*;
import javax.xml.transform.TransformerFactoryConfigurationError;
import java.io.*;

/**
 * Created by galloisg on 08/07/2016.
 */
public class Staxon {

    // see : http://www.programcreek.com/java-api-examples/index.php?api=de.odysseus.staxon.json.JsonXMLConfig

    public static String TEST_XML_STRING =
            "<?xml version=\"1.0\"?>\n" +
                    "<feed xmlns=\"http://www.w3.org/2005/Atom\">\n" +
                    "  <entry id=\"1\">\n" +
                    "    <name>Homer Simpson</name>\n" +
                    "    <birthday>1956-03-01</birthday>\n" +
                    "    <email xmlns=\"http://www.w3.org/2007/app\">chunkylover53@aol.com</email>\n" +
                    "    <phoneNumbers>\n" +
                    "      <home>5551234</home>\n" +
                    "      <mobile>5555678</mobile>\n" +
                    "      <work>5559991</work>\n" +
                    "    </phoneNumbers>\n" +
                    "  </entry>\n" +
                    "  <entry id=\"2\">\n" +
                    "    <name>Marge Simpson</name>\n" +
                    "    <birthday>1965-03-01</birthday>\n" +
                    "    <email xmlns=\"http://www.w3.org/2007/app\">chunkylover53@aol.com</email>\n" +
                    "    <phoneNumbers>\n" +
                    "      <home>152154</home>\n" +
                    "      <work>18644</work>\n" +
                    "    </phoneNumbers>\n" +
                    "  </entry>\n" +
                    "</feed>";

    public static String SIMPE_XML = "<?xml version=\"1.0\"?>\n" +
            "<customer>\n" +
            "    <first-name>Jane</first-name>\n" +
            "    <last-name>Doe</last-name>\n" +
            "    <address>\n" +
            "        <street>123 A Street</street>\n" +
            "    </address>\n" +
            "    <phone-number type=\"work\">555-1111</phone-number>\n" +
            "    <phone-number type=\"cell\">555-2222</phone-number>\n" +
            "</customer>";

    public static String convertToJson(String xml_data, PrintWriter out) {
        // Conversion based on https://github.com/beckchr/staxon/wiki/Converting-XML-to-JSON
    /*
     * If we want to insert JSON array boundaries for multiple elements, we need to set the <code>autoArray</code>
	 * property. If our XML source was decorated with <code>&lt;?xml-multiple?&gt;</code> processing instructions,
	 * we'd set the <code>multiplePI</code> property instead.
	 */
        JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true).prettyPrint(true).build();
        InputStream input = null;
        OutputStream output = null;
        String ret = "error";
        try {
            input = new ByteArrayInputStream(xml_data.getBytes("UTF-8"));
            output = new ByteArrayOutputStream();
		/*
		 * Create reader (XML).
		 */
            XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(input);

		/*
		 * Create writer (JSON).
		 */
            XMLEventWriter writer = new JsonXMLOutputFactory(config).createXMLEventWriter(output);

		/*
		 * Copy events from reader to writer.
		 */
            writer.add(reader);

		/*
		 * Close reader/writer.
		 */
            reader.close();
            writer.close();
            ret = output.toString();
        } catch (UnsupportedEncodingException ue) {
            out.print("<font color='red'>Unsupported encoding during conversion: XML to JSON.</font>");
        } catch (XMLStreamException se) {
            out.print("<font color='red'>XML Stream exception during conversion: XML to JSON.</font>");
            se.printStackTrace();
        } catch (FactoryConfigurationError fe) {
            out.print("<font color='red'>Factory configuration error during conversion: XML to JSON.</font>");
            fe.printStackTrace();
        } catch (TransformerFactoryConfigurationError tfe) {
            out.print("<font color='red'>Transformer factory configuration error during conversion: XML to JSON.</font>");
            tfe.printStackTrace();
        } finally {
		/*
		 * As per StAX specification, XMLEventReader/Writer.close() doesn't close the underlying stream.
		 */
            if (output != null)
                try {
                    output.close();
                } catch (IOException e) {
                }
            if (input != null)
                try {
                    input.close();
                } catch (IOException e) {
                }
        }
        return ret;
    }

    public static void main(String[] args) {
        PrintWriter pw = new PrintWriter(System.out, true);
        System.out.println(convertToJson(SIMPE_XML, pw));

    }

}
