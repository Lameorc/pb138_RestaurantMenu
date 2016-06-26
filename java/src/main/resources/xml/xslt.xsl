<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="xml"
                doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
                doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
                encoding="UTF-8"
                indent="yes"
    />
    <xsl:template match="menus">
        <HTML>
          <HEAD><title>Jídelní lístek</title></HEAD>
          <BODY>
                <xsl:apply-templates select="day"/> 
          </BODY>
        </HTML>
    </xsl:template>

    <xsl:template match="day"> 
      
        <b><xsl:value-of select="@name"/><span> - </span><xsl:value-of select="@date"/></b>
        <xsl:apply-templates select="meal"/> 

         
    </xsl:template>
    
    <xsl:template match="meal">
        <xsl:value-of select="@number"/> <xsl:value-of select="weight/text()"/>
        <xsl:value-of select="name/text()"/> <xsl:value-of select="price/text()"/>
    </xsl:template> 

</xsl:stylesheet>
