
package by.home.project.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Parameter implements Serializable {

	private static final long serialVersionUID = -137772978916211938L;

	private String productType;
	private Map<String, String> parameter = new HashMap<String, String>();

	public Parameter() {
	}

	public Parameter(Map<String, String> parameter) {
		this.parameter = parameter;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Map<String, String> getParameter() {
		return parameter;
	}

	public void setParameter(Map<String, String> parameter) {
		this.parameter = parameter;
	}

	public void add(String searchParameter, String value) {
		parameter.put(searchParameter, value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parameter == null) ? 0 : parameter.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parameter other = (Parameter) obj;
		if (parameter == null) {
			if (other.parameter != null)
				return false;
		} else if (!parameter.equals(other.parameter))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parameter [productType=" + productType + ", parameter=" + parameter + "]";
	}

}
