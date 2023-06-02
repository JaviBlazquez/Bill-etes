<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
	
	<p><b>Client:</b></p>
	<p> Name: <xsl:value-of select="Client/@name" /> </p>
	<p> Surname: <xsl:value-of select="Client/@surname" /> </p>
	<p> Phone: <xsl:value-of select="Client/phone" /></p>
	<p> Money: <xsl:value-of select="Client/money" /></p>
	<p> Condition: <xsl:value-of select="Client/condition" /></p>
	   
   </html>
   </xsl:template>
	
</xsl:stylesheet>