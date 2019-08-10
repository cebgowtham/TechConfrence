        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw=response.getWriter();
        try
        {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost\\sqlexpress;database=Grivenace","sa","12345");
            
            Statement st=con.createStatement();
            
            ResultSet rs=st.executeQuery("select * from grid");
            int col=rs.getMetaData().getColumnCount();
            pw.println("No. of column : "+col);
           
            String colname[]=new String[col];
            
            for (int i=0;i<col;i++)
            {
            //    pw.println("col name = "+rs.getMetaData().getColumnName(i) + "    data type "+rs.getMetaData().getColumnClassName(i));
            colname[i]=rs.getMetaData().getColumnName(i+1);
            }
                      pw.println("<html><center><table border='1'><tr>");
                      
                      for(int k=0;k<col;k++)
                      {
                          pw.println("<th>"+colname[k]+"</th>");
                      }
                      pw.println("</tr>");
            while(rs.next()==true)
            {  
                pw.println("<tr>");
                for(int j=0;j<col;j++)
                {
                    
                   pw.print("<td>"+rs.getString(colname[j])+"</td>");
                }
                pw.println("</tr>");
            }
            pw.println("</table></center></html>");
            
            
            rs.close();
            st.close();
            con.close();
        }
        catch(Exception e)
        {  pw.println("Error message:"+e.toString());
        }