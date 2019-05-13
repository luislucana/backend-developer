package br.com.blog.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
public class DateMapper {
	
	//private static final String DATE_PATTERN = "dd/MM/yyyy hh24:mi:ss";
	private static final String DATE_PATTERN = "yyyy-MM-dd";

    public String asString(Date date) {
        return date != null ? new SimpleDateFormat(DATE_PATTERN).format(date) : null;
    }

    public Date asDate(String date) {
        try {
            return date != null ? new SimpleDateFormat(DATE_PATTERN).parse(date) : null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}