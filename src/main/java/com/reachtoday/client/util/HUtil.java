package com.reachtoday.client.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class HUtil {

	public int getPatientAgeInMonths(String DOB){
		
		int age =0;
		int month = 0;
		
		if (!DOB.equals("") && DOB != null) {
			Date currentDateTime = new Date();
			DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
			String formattedDate = dateFormat.format(currentDateTime);

			int birthYear = Integer.parseInt(DOB.substring(0, DOB.indexOf('-')));
			int birthMonth = Integer.parseInt(DOB.substring(DOB.indexOf('-')+1, DOB.lastIndexOf('-')));
			int birthDate = Integer.parseInt(DOB.substring(DOB.lastIndexOf('-')+1));

			int currentYear = Integer.parseInt(formattedDate.substring(0, formattedDate.indexOf('-')));
			int currentMonth = Integer.parseInt(formattedDate.substring(formattedDate.indexOf('-')+1, formattedDate.lastIndexOf('-')));
			int currentDate = Integer.parseInt(formattedDate.substring(formattedDate.lastIndexOf('-')+1));

			age = Math.abs(currentYear - birthYear);
			month = Math.abs(currentMonth - birthMonth);

			if (currentMonth < birthMonth) {
				age = age - 1;
				month = 12 - month;
			} else if (currentMonth == birthMonth && currentDate < birthDate) {
				age = age - 1;
				month = month - 1;
			}	
		}
		return month+age*12;
	}
	
	public static String Replace(String xStringValue, String xReplaceString,
			String xNewString) {
		int startPosition, stringLength;
		stringLength = xReplaceString.length();
		if (xStringValue.indexOf(xReplaceString) != -1) {
			startPosition = xStringValue.length();
			startPosition = xStringValue.indexOf(xReplaceString);
			xStringValue = xStringValue.substring(0, startPosition)
					+ xNewString
					+ Replace(xStringValue.substring(startPosition
							+ stringLength, xStringValue.length()),
							xReplaceString, xNewString);
		}
		return xStringValue;
	}

	public static boolean checkNull(String xParam) {
		xParam = (xParam != null) ? xParam.trim() : xParam;
		if (xParam == null || xParam.length() == 0 || xParam == "")
			return true;
		else
			return false;
	}

	public static String ReplaceString(String xStringValue) {
		String[] replaceArray = new String[] { "E", "X", "T", "N", "e", "x",
				"t", "n", "(", ")", " ", "-" };
		for (int i = 0; i < replaceArray.length; i++) {
			xStringValue = Replace(xStringValue, replaceArray[i], "");
		}
		return xStringValue;
	}

	public static String FormatPhoneNumber(String xPhoneNo) {
		String formatedPhoneNo;
		formatedPhoneNo = xPhoneNo;
		if (xPhoneNo == null || xPhoneNo.equals("-1"))
			return "";
		if (!checkNull(xPhoneNo)) {
			xPhoneNo = ReplaceString(xPhoneNo);
			xPhoneNo = xPhoneNo.trim();
			formatedPhoneNo = xPhoneNo;

			if (xPhoneNo.indexOf('(') >= 0 || xPhoneNo.indexOf('-') >= 0
					|| xPhoneNo.indexOf(')') >= 0)
				return formatedPhoneNo;

			if (xPhoneNo.length() == 7)
				formatedPhoneNo = xPhoneNo.substring(0, 2) + "-"
						+ xPhoneNo.substring(3, xPhoneNo.length() - 1);
			else if (xPhoneNo.length() > 7 && xPhoneNo.length() <= 10)
				formatedPhoneNo = "(" + xPhoneNo.substring(0, 3) + ") "
						+ xPhoneNo.substring(3, 6) + "-"
						+ xPhoneNo.substring(6, xPhoneNo.length());
			else if (xPhoneNo.length() > 10)
				formatedPhoneNo = "(" + xPhoneNo.substring(0, 3) + ") "
						+ xPhoneNo.substring(3, 6) + "-"
						+ xPhoneNo.substring(6, 10) + " Extn-"
						+ xPhoneNo.substring(10, xPhoneNo.length());
		}
		return formatedPhoneNo;
	}
}
