package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import eu.ark.creditark.services.creditarkservices.enums.GenerealConstants;
import eu.ark.creditark.services.creditarkservices.utils.DateUtils;
import eu.ark.creditark.services.creditarkservices.utils.DtoGenUtils;
import org.apache.logging.log4j.util.Strings;

public class KeyValueMultipleDto extends KeyValueDto implements Serializable{

	private static final long serialVersionUID = -3342627858365858707L;
	private String[] values;

	public KeyValueMultipleDto() {
	}

	public KeyValueMultipleDto(String key, String value, String[] values) {
		super(key, value);
		this.values = values;
	}
	
	public KeyValueMultipleDto(String key, String value, int[] values) {
		super(key, value);
		this.values = Arrays.stream(values).mapToObj(val -> Strings.EMPTY + val).toArray(String[]::new);
	}
	
	public KeyValueMultipleDto(String key, String value, double[] values) {
		super(key, value);

		this.values = Arrays.stream(values).mapToObj(val -> DtoGenUtils.numberToStringUIFormated(val)).toArray(String[]::new);
	}
	
	public KeyValueMultipleDto(String key, String value, Date[] values) {


		super(key, value);
		this.values = Arrays.stream(values).map(val -> {
					try {
						return null==val?"":DateUtils.dateToString(DateUtils.formatDate(
								new Date(val.getTime()), GenerealConstants.DATE_FORMAT_DD_MM_YYYY.getValue()
						), GenerealConstants.DATE_FORMAT_DD_MM_YYYY.getValue());
					} catch (ParseException e) {
					}
					return "";
				}
		).toArray(String[]::new);
	}
	public String[] getValues() {
		return values;
	}
	public void setValues(String[] values) {
		this.values = values;
	}
	
	
	
}
