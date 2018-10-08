package com.project.manage.service.projectmanageservice.util;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectManagementUtility {

	public static <T> void copyObjects(T copyFrom, T copyTo) {
		Field[] copyFromFields = copyFrom.getClass().getDeclaredFields();
		Field[] copyToFields = copyTo.getClass().getDeclaredFields();
		for (Field copyFromField : copyFromFields) {
			for (Field copyToField : copyToFields) {
				if ((copyFromField.getName().equalsIgnoreCase(copyToField.getName())
						&& copyFromField.getType().getName().equalsIgnoreCase(copyToField.getType().getName()))) {
					try {
						copyFromField.setAccessible(true);
						copyToField.setAccessible(true);
						copyToField.set(copyTo, copyFromField.get(copyFrom));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				} else if (copyFromField.getName().equalsIgnoreCase(copyToField.getName())
						&& !copyFromField.getType().getName().equalsIgnoreCase(copyToField.getType().getName())) {
					try {
						copyFromField.setAccessible(true);
						copyToField.setAccessible(true);
						if (null != copyFromField.get(copyFrom)) {
							if (copyToField.getType().getName().equalsIgnoreCase("java.util.Date")) {
								Date dateObject = convertStringToDate(copyFromField.get(copyFrom).toString());
								copyToField.set(copyTo, dateObject);
							} else if (copyFromField.getType().getName().equalsIgnoreCase("java.util.Date")) {
								copyFromField.setAccessible(true);
								copyToField.setAccessible(true);
								String dateString = convertDateToString((Date) copyFromField.get(copyFrom));
								copyToField.set(copyTo, dateString);
							}
						}
					} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static Date convertStringToDate(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateObject = null;
		try {
			dateObject = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateObject;
	}

	public static String convertDateToString(Date dateObject) {
		if (null != dateObject) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.format(dateObject);
		}
		return null;

	}

}
