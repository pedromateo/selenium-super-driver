package org.superdriver;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.*;
/**
 * 
 * @author Adrian Nicolas Conesa,...
 *
 */
public class SuperFilter {
	@SuppressWarnings("unused")
	private SuperDriver _sd;
	//Parameters
	private String att_key = "";
	private String att_value = "";
	private boolean att_enabled = false;
	private boolean att_em_enabled = false;
	private String txt_value = "";
	private boolean txt_enabled = false;
	private boolean txt_em_enabled = false;
	private String css_key = "";
	private String css_value = "";
	private boolean css_enabled = false;
	private boolean css_em_enabled = false;
	private String tag_value = "";
	private boolean tag_enabled = false;
	private boolean tag_em_enabled = false;


	/**
	 * Constructor
	 * @param sd
	 */
	public SuperFilter(SuperDriver sd) {
		_sd = sd;
	}
	/**
	 * Condition to filter by attribute a List<WebElement>.
	 * 
	 * 				List<WebElement>
						.stream()
						.filter(a -> a.getAttribute(key).contain(value))
						.collect(Collectors.toList());

	 * @param String key
	 * @param String value
	 * @param exactmatch (true/false)
	 * @return
	 */
	public SuperFilter ByAttribute(String key, String value, boolean exactmatch) {
		att_em_enabled = exactmatch;
		att_enabled = true;
		att_key = key;
		att_value = value;
		return this;
	}
	/**
	 * Condition to filter by InnerText a List<WebElement>.
	 * 
	 * 				List<WebElement>
						.stream()
						.filter(a -> a.getText().contain(value))
						.collect(Collectors.toList());

	 * @param String value
	 * @param String exactmatch (true/false)
	 * @return
	 */
	public SuperFilter ByInnerText(String value, boolean exactmatch) {
		txt_em_enabled = exactmatch;
		txt_enabled = true;
		txt_value = value;
		return this;
	}
	/**
	 * Condition to filter by CSSValue a List<WebElement>.
	 * 
	 * 				List<WebElement>
						.stream()
						.filter(a -> a.getCssValue(key).contain(value))
						.collect(Collectors.toList());

	 * @param String key
	 * @param String value
	 * @param exactmatch (true/false)
	 * @return
	 */
	public SuperFilter ByCssValue(String key, String value, boolean exactmatch) {
		css_em_enabled = exactmatch;
		css_enabled = true;
		css_key = key;
		css_value = value;
		return this;
	}
	/**
	 * Condition to filter by TagName a List<WebElement>.
	 * 				List<WebElement>
						.stream()
						.filter(a -> a.getTagName().contains(value))
						.collect(Collectors.toList());	

	 * @param String value
	 * @param String exactmatch (true/false)
	 * @return
	 */
	public SuperFilter ByTagName(String value, boolean exactmatch) {
		tag_em_enabled = exactmatch;
		tag_enabled = true;
		tag_value = value;
		return this;
	}
	/**
	 * Condition to filter by TagName a List<WebElement>.
	 * 				List<WebElement>
						.stream()
						.filter(a -> a.getTagName().contains(value))
						.collect(Collectors.toList());	

	 * @param String value
	 * @param String exactmatch = false
	 * @return
	 */
	public SuperFilter ByTagName(String value) {
		return ByTagName(value,false);
	}
	/**
	 * Condition to filter by CSSValue a List<WebElement>.
	 * 
	 * 				List<WebElement>
						.stream()
						.filter(a -> a.getCssValue(key).contain(value))
						.collect(Collectors.toList());

	 * @param String key
	 * @param String value
	 * @param exactmatch = false
	 * @return
	 */
	public SuperFilter ByCssValue(String key, String value) {
		return ByCssValue(key, value, false);
	}
	/**
	 * Condition to filter by InnerText a List<WebElement>.
	 * 
	 * 				List<WebElement>
						.stream()
						.filter(a -> a.getText().contain(value))
						.collect(Collectors.toList());

	 * @param String value
	 * @param String exactmatch = false
	 * @return
	 */
	public SuperFilter ByInnerText(String value) {
		return ByInnerText(value, false);
	}
	/**
	 * Condition to filter by InnerText a List<WebElement>.
	 * 
	 * 				List<WebElement>
						.stream()
						.filter(a -> a.getText().contain(value))
						.collect(Collectors.toList());

	 * @param String value
	 * @param String exactmatch = false
	 * @return
	 */
	public SuperFilter ByAttribute(String key, String value) {
		return ByAttribute(key, value, false);
	}
	/**
	 * This method applies all the chained filters on a List<WebElement>.
	 * @param List<WebElement>
	 * @return Filtered List<WebElement>
	 */
	public List<WebElement> applyFilter (List<WebElement> webElementlist) {
		//Check if the list of WebElements is empty
		if (webElementlist.size()<0) { 
			return webElementlist;
		}
		else {
			List<WebElement> filteredlist = webElementlist;
			if (att_enabled && att_key != "") {
				if (att_em_enabled) {
					filteredlist = filteredlist
							.stream()
							.filter(a -> a.getAttribute(att_key).contentEquals(att_value))
							.collect(Collectors.toList());
					System.out.println(att_key + att_value);

				}
				else {
					filteredlist = filteredlist
							.stream()
							.filter(a -> a.getAttribute(att_key).contains(att_value))
							.collect(Collectors.toList());
				}	
			}
			//
			if (css_enabled && (css_key != "" && css_value != "")) {
				if (css_em_enabled) {
					filteredlist = filteredlist
							.stream()
							.filter(a -> a.getCssValue(css_key).contentEquals(css_value))
							.collect(Collectors.toList());
				}
				else {
					filteredlist = filteredlist
							.stream()
							.filter(a -> a.getCssValue(css_key).contains(css_value))
							.collect(Collectors.toList());
				}
			}
			if (tag_enabled && tag_value != "") {
				if (tag_em_enabled) {
					filteredlist = filteredlist
							.stream()
							.filter(a -> a.getTagName().contentEquals(tag_value))
							.collect(Collectors.toList());
				}
				else {
					filteredlist = filteredlist
							.stream()
							.filter(a -> a.getTagName().contains(tag_value))
							.collect(Collectors.toList());
				}
			}
			if (txt_enabled && txt_value != "") {
				if (txt_em_enabled) {
					filteredlist = filteredlist
							.stream()
							.filter(a -> a.getText().contentEquals(txt_value))
							.collect(Collectors.toList());
				}
				else {
					filteredlist = filteredlist
							.stream()
							.filter(a -> a.getText().contains(txt_value))
							.collect(Collectors.toList());
				}	
			}
			return filteredlist;
		}
	}
	/**
	 * Get a List<String> of the attributes of List<WebElement>.
	 * 
	 * 				mappedlist = List<WebElement>
						.stream()
						.map(a -> a.getAttribute(key).toString())
						.collect(Collectors.toList());

					return mappedlist;


	 * @param List<WebElement>
	 * @param key
	 * @return List<String>
	 */
	public List<String> mapListOfAttributes(List<WebElement> webElementlist, String key) {
		List<String> mappedlist = null;
		if (webElementlist.size()>0) {
			mappedlist = webElementlist
					.stream()
					.map(a -> a.getAttribute(key).toString())
					.collect(Collectors.toList());
		}
		return mappedlist;
	}
	/**
	 * Get a List<String> of the Text of List<WebElement>.
	 * 
	 * 				mappedlist = List<WebElement>
						.stream()
						.map(a -> a.getText().toString())
						.collect(Collectors.toList());

					return mappedlist;


	 * @param List<WebElement>
	 * @return List<String>
	 */
	public List<String> mapListOfText(List<WebElement> webElementlist) {
		List<String> mappedlist = null;
		if (webElementlist.size()>0) {
			mappedlist = webElementlist
					.stream()
					.map(a -> a.getText().toString())
					.collect(Collectors.toList());
		}
		return mappedlist;
	}
	/**
	 * Clean all the conditions of the filter.
	 */
	public void clearFilter() {
		this.att_key = "";
		this.att_value = "";
		this.att_enabled = false;
		this.att_em_enabled = false;
		this.txt_value = "";
		this.txt_enabled = false;
		this.txt_em_enabled = false;
		this.css_key = "";
		this.css_value = "";
		this.css_enabled = false;
		this.css_em_enabled = false;
		this.tag_value = "";
		this.tag_enabled = false;
		this.tag_em_enabled = false;
	}
}
