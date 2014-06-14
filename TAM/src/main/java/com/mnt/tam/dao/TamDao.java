/**
 * 
 */
package com.mnt.tam.dao;

import java.util.List;

/**
 * @author venkateshp
 * 
 */
public interface TamDao {
	public String saveDetails(Object object);

	public List<Object[]> searchDetails(String sql);

	public String updateDetails(Object object);

	public String deleteDetails(Object object);

	public String deleteAllDetails(List<Object> objects);

	public Long duplicateCheck(String sql);

	public String saveAllDetails(List<Object> object);
}
