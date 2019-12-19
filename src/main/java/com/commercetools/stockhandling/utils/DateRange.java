/**
 * 
 */
package com.commercetools.stockhandling.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author M.Othman
 *
 */
public class DateRange {

	private static LocalDate localNow = LocalDate.now();

	public static Date getDateForSearch(String timeParamaValue) {

		Date result = null;

		switch (timeParamaValue.toLowerCase()) {
		case "today":
			result = getTodayDate();
			break;
		case "lastmonth":
			result = getLastMonthDate();
			break;
		default:
			throw new StockHandlingException(StatusCode.BADREQUEST, "time parameter value not recognized");
		}

		return result;
	}

	public static Date getTodayDate() {

		return Date.from(localNow.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static Date getLastMonthDate() {

		LocalDate monthStart = localNow.minusMonths(1).withDayOfMonth(1);

		return Date.from(monthStart.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
