package com.frsummit.HRM.api.postprocessing;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class SortPostProcessing<T> implements PostProcessing<List<T>> {

	private boolean sort;
	private String sortAttribute;
	private String sortOrder;

	public SortPostProcessing(boolean sort, String sortAttribute, String sortOrder) {
		this.sort = sort;
		this.sortAttribute = sortAttribute;
		this.sortOrder = sortOrder;
	}

	public boolean isSort() {
		return sort;
	}

	public String getSortAttribute() {
		return sortAttribute;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	@Override
	public List<T> process(List<T> list) throws Exception {
		List<T> result = list;
		if (sort) {
			if (list.size() > 0) {
				T temp = list.get(0);
				boolean hasAttribute = Arrays.stream(temp.getClass().getDeclaredFields()).anyMatch(f -> {
					return f.getName().equals(sortAttribute);
				});
				System.out.println(hasAttribute);
				if (hasAttribute) {
					result.sort((a, b) -> {
						try {
							Comparable attributeValueA = (Comparable) new PropertyDescriptor(sortAttribute,
									a.getClass()).getReadMethod().invoke(a);
							Comparable attributeValueB = (Comparable) new PropertyDescriptor(sortAttribute,
									b.getClass()).getReadMethod().invoke(b);
							if (sortOrder.equals("asc")) {
								return attributeValueA.compareTo(attributeValueB);
							} else if (sortOrder.equals("desc")) {
								return -attributeValueA.compareTo(attributeValueB);
							} else {
								// TODO create a custom exception here
								throw new Exception(
										"Invalid request ! The parameter sort_order can only take two values : asc, desc.");
							}
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
								| IntrospectionException e1) {
							e1.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
						return 0;
					});
				} else {
					// TODO create a custom exception here
					throw new Exception("Invalid request ! Attribute unrecognized");
				}
			}
		}

		return result;
	}

}
