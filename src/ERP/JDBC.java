package ERP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JDBC {
	public JDBC(){
		
	}

	public static List Query(String sql){
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/ERP";
		String user="root";
		String password="";
		try {
			Class.forName(driver).newInstance();
			Connection connection=DriverManager.getConnection(url, user, password);
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(sql);
			List list=ResultSetToList(resultSet);
			resultSet.close();
			statement.close();
			connection.close();
			return list;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		} 
	}
	
	public static List ResultSetToList(ResultSet resultSet) throws java.sql.SQLException{   
		if (resultSet==null)   
			return Collections.EMPTY_LIST;   
		ResultSetMetaData metaDate=resultSet.getMetaData();   
		int columnCount=metaDate.getColumnCount(); 
		List list=new ArrayList(); 
		Map columnNames=new HashMap(columnCount);
		for(int i=1;i<=columnCount;i++){
			columnNames.put(i,metaDate.getColumnName(i));
		}
		list.add(columnNames);
		while (resultSet.next()) {   
			Map rowData=new HashMap(columnCount);   
			for (int i=1;i<=columnCount;i++) {   
				rowData.put(i, resultSet.getObject(i));   
			}   
			list.add(rowData);   
		}   
		return list;   
	}
}
