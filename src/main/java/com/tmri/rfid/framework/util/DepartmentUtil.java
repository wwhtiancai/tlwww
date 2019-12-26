package com.tmri.rfid.framework.util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.tmri.rfid.framework.bean.Department;

public class DepartmentUtil{
private List<String> returnList = new ArrayList<String>();
	
	/**
	 * 根据父节点的ID获取所有子节点
	 * @param list 分类表
	 * @param typeId 传入的父节点ID
	 * @return String
	 */
	public List<String> getChildDepartments(List<Department> list,String glbm) {
		if(list == null || glbm == null || glbm.length()<1) return null;
		for (Iterator<Department> iterator = list.iterator(); iterator.hasNext();) {
			Department Department = (Department) iterator.next();
			if (glbm.equals(Department.getGlbm())) {
				recursionFn(list, Department);
			}
		}
		return returnList;
	}
	
	private void recursionFn(List<Department> list, Department Department) {
		List<Department> childList = getChildList(list, Department);// 得到子节点列表
		if (hasChild(list, Department)) {// 判断是否有子节点
			returnList.add(Department.getGlbm());
			Iterator<Department> it = childList.iterator();
			while (it.hasNext()) {
				Department n = (Department) it.next();
				recursionFn(list, n);
			}
		} else {
			returnList.add(Department.getGlbm());
		}
	}
	
	private List<Department> getChildList(List<Department> list, Department Department) {
		List<Department> DepartmentList = new ArrayList<Department>();
		Iterator<Department> it = list.iterator();
		while (it.hasNext()) {
			Department n = (Department) it.next();
			if(!"".equals(n.getSjbm())&&n.getSjbm()!=null){
				if (n.getSjbm().equals(Department.getGlbm())) {
					DepartmentList.add(n);
				}
			}
		}
		return DepartmentList;
	}

	private boolean hasChild(List<Department> list, Department Department) {
		return getChildList(list, Department).size() > 0 ? true : false;
	}
	
}
