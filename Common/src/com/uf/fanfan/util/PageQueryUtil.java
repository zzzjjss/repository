package com.uf.fanfan.util;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.uf.fanfan.common.PageQueryResult;
import com.uf.fanfan.entity.Product;

public class PageQueryUtil {
	public static String convertToFlexigridJson(PageQueryResult<?> result,String fields[],String actionContent){
		StringBuilder res=new StringBuilder();
		res.append("{");
		res.append("\"page\":"+result.getPageIndex()+",\"total\":"+result.getTotalRecord());
		List<?> data=result.getPageData();
		if(data!=null&&data.size()>0){
			res.append(",\"rows\":[");
			int recordLen=data.size();
			for(int j=0;j<recordLen;j++){
				res.append("{");
				try {
					Object row=data.get(j);
					Field idField=row.getClass().getDeclaredField("id");
					idField.setAccessible(true);
					Object id=idField.get(row);
					String rowID=objectToString(id);
					res.append("\"id\":\""+rowID+"\"");
					int fieldLen=fields.length;
					if(fieldLen>0){
						res.append(",\"cell\":[");
						for(int i=0;i<fieldLen;i++){
							Field f=row.getClass().getDeclaredField(fields[i]);
							f.setAccessible(true);
							Object value=f.get(row);
							String strValue=objectToString(value);
							res.append("\""+strValue+"\"");
							if(i<fieldLen-1){
								res.append(",");
							}else{
								if(!StringUtil.isNullOrEmpty(actionContent)){
									res.append(","+"\"<a onclick='rowProcessAction("+rowID+")' href='#'>[&nbsp;"+actionContent+"&nbsp;]</a>\"");
								}
							}
						}
						res.append("]");
					}
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				res.append("}");
				if(j<recordLen-1)
					res.append(",");
			}
			res.append("]");
		}
		res.append("}");
		return res.toString();
		
	}
	public static String objectToString(Object obj){
		if(obj==null||obj.toString()==null)
			return "";
		//repleace the " to '
		if(obj instanceof Double){
			Double dou=(Double)obj;
			DecimalFormat format=new DecimalFormat("###,###,###.00");
			return format.format(dou);
		}else if(obj instanceof Timestamp){
			Timestamp time=(Timestamp)obj;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return formatter.format(time);
		}
		
		return obj.toString().replaceAll("\"", "'");
	}
	public static void main(String[] args) {
		PageQueryResult<Product>  re=new PageQueryResult<Product>();
		List<Product> pr=new ArrayList<Product>();
		Product  p=new Product();
		p.setCreateTime(new Timestamp(System.currentTimeMillis()));
		p.setDescription("decription");
		p.setId(1);
		p.setName("tudoushaorou");
		p.setPrice(32.3d);
		pr.add(p);
		Product  p1=new Product();
		p1.setCreateTime(new Timestamp(System.currentTimeMillis()));
		p1.setDescription("decription");
		p1.setId(2);
		p1.setName("fffffffff");
		p1.setPrice(32.33d);
		pr.add(p1);
		re.setPageData(pr);
		re.setPageIndex(1);
		re.setPageSize(1);
		re.setTotalPage(2);
		re.setTotalRecord(100);
		System.out.println(convertToFlexigridJson(re,new String[]{"createTime","description","name","price"},"删除"));
	}
}
