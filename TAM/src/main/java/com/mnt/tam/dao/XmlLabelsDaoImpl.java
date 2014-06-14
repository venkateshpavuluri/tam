/**
 *  @Copyright MNTSOFT
 */
package com.mnt.tam.dao;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mnt.tam.daoSupport.TamDaoSupport;

import javax.servlet.ServletContext;

/**
 * @author Naresh
 * @version 1.0 10-10-2013
 * 
 */
@Repository("xmlDao")
public class XmlLabelsDaoImpl  implements
		XmlLabelsDao {
	
	Logger log = Logger.getLogger(XmlLabelsDaoImpl.class);

	@Autowired
	ServletContext servletContext;

	public Map<String, String> populateXmlLabels(String name) {

		Map<String, String> map = new LinkedHashMap<String, String>();
		try {

			String filePath = servletContext
					.getRealPath("/Resources/context.xml");
			File fXmlFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			// log.info("Root element :" +
			// doc.getDocumentElement().getNodeName());

			NodeList list = doc.getElementsByTagName("context");

			for (int x = 0; x < list.getLength(); x++) {

				Node node = list.item(x);
				// log.info("\nCurrent Element :" +
				// node.getNodeName());

				Element eElement = (Element) node;
				/*
				 * log.info("field : " +
				 * eElement.getElementsByTagName("field").item(0)
				 * .getTextContent());
				 */

				if (eElement.getAttribute("id").equals(name)) {

					NodeList sublist = node.getChildNodes();

					for (int y = 0; y < sublist.getLength(); y++) {
						Node finNode = sublist.item(y);
						if (finNode.getNodeType() == Node.ELEMENT_NODE) {
							Element ele = (Element) finNode;
							if (ele.getAttribute("visible").equals("true")) {
								map.put(ele.getAttribute("dbfield"),
										ele.getAttribute("label"));
							}
						}

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}

	public List<Object[]> populateXml(String name) {
		List<Object[]> returnString = null;

		try {

			String filePath = servletContext
					.getRealPath("/Resources/context.xml");
			File fXmlFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			returnString = new ArrayList<Object[]>();
			NodeList list = doc.getElementsByTagName("context");
			for (int x = 0; x < list.getLength(); x++) {

				Node node = list.item(x);
				Element eElement = (Element) node;
				if (eElement.getAttribute("id").equals(name)) {
					NodeList advanceList = eElement
							.getElementsByTagName("advanceSearch");
					for (int ax = 0; ax < advanceList.getLength(); ax++) {
						Node nodeAdvance = advanceList.item(ax);
						NodeList sublist = nodeAdvance.getChildNodes();
						for (int y = 0; y < sublist.getLength(); y++) {
							Node finNode = sublist.item(y);
							if (finNode.getNodeType() == Node.ELEMENT_NODE) {
								Element ele = (Element) finNode;
								Object[] o = new Object[sublist.getLength()];
								o[0] = ele.getAttribute("dbfield");
								o[1] = ele.getAttribute("label");
								returnString.add(o);
							}

						}

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;

	}

}
