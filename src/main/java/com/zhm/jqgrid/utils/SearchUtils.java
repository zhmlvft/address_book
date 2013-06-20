package com.zhm.jqgrid.utils;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.zhm.jqgrid.model.SearchBean;
import com.zhm.jqgrid.model.SearchFieldsBean;

public class SearchUtils {
	private static String generateSearchCond(SearchBean sb,StringBuffer where)
	{
		String opt = sb.getGroupOp();
		List<SearchFieldsBean> sfbs = sb.getRules();
		for(SearchFieldsBean tmp:sfbs)
		{
			where.append(" "+opt+" ");
			where.append(tmp.getField());
			String op = tmp.getOp();
			String data = tmp.getData().replaceAll("\'", "\'\'");
			if("eq".equals(op))
			{
				where.append(" = '"+data+"'");
			}
			else if("ne".equals(op))
			{
				where.append(" <> '"+data+"'");
			}
			else if("lt".equals(op))
			{
				where.append(" < '"+data+"'");
			}
			else if("le".equals(op))
			{
				where.append(" <= '"+data+"'");
			}
			else if("gt".equals(op))
			{
				where.append(" > '"+data+"'");
			}
			else if("ge".equals(op))
			{
				where.append(" >= '"+data+"'");
			}
			else if("bw".equals(op))
			{
				where.append(" like '"+data+"%'");
			}
			else if("bn".equals(op))
			{
				where.append(" not like '"+data+"%'");
			}
			else if("in".equals(op))
			{
				where.append(" in ('"+data+"')");
			}
			else if("ni".equals(op))
			{
				where.append(" not in ('"+data+"')");
			}
			else if("ew".equals(op))
			{
				where.append(" like '%"+data+"'");
			}
			else if("en".equals(op))
			{
				where.append(" not like '%"+data+"'");
			}
			else if("cn".equals(op))
			{
				where.append(" like '%" +data+"%'");
			}
			else if("nc".equals(op))
			{
				where.append(" not like '%"+data+"%'");
			}
		}
		return where.toString();
	}
	public static String convertSearchCond(String filters) {
		// TODO Auto-generated method stub
		SearchBean sb = null;
		if(filters!=null&&!"".equals(filters))
		{
			ObjectMapper objectMapper = new ObjectMapper(); 
			try
			{
				sb = objectMapper.readValue(filters, SearchBean.class);
			}
			catch (JsonParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (JsonMappingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
		String cond = "";
		if(sb!=null)
		{
			StringBuffer where = new StringBuffer();
			cond =generateSearchCond(sb, where);
		}
		return cond;
	}
}
