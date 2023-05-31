<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
	
	<p><b>Casino:</b></p>
	<p> Name: <xsl:value-of select="Casino/@name" /> </p>
	<p> Workers:   <xsl:value-of select="Casino/workers" /> </p>
	
	<table border="1">
	  <th>Name</th>
      <th>Surname</th>
      <th>Salary</th>
      <th>Addres</th>
      <th>Occupation</th>
      
      <xsl:for-each select="Casino/Workers/worker">
      <xsl:sort select="@name" />
	       <tr>
	            <td><xsl:value-of select="@name" /></td>
	            <td><xsl:value-of select="@surname" /></td>
	            <td><xsl:value-of select="salary" /></td>
	            <td><xsl:value-of select="addres" /></td>
	            <td><xsl:value-of select="occupation" /></td>            
	       </tr>        
      </xsl:for-each>  
		
	</table>		
	   
   </html>
   </xsl:template>
	
</xsl:stylesheet>