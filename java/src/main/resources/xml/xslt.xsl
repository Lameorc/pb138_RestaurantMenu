<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="html"
                doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
                doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
                encoding="UTF-8"
                indent="yes"
    />
    <xsl:template match="menus">
        <HTML>
          <HEAD> <style> ul li {list-style-type: none} </style></HEAD>
          <BODY>
            <h1> Jídelní lístek </h1>
            <ul>  
                <xsl:apply-templates select="day"/>  
            </ul>
          </BODY>
        </HTML>
    </xsl:template>

    <xsl:template match="day"> 
      <li>
        <b><xsl:value-of select="@name"/> - <xsl:value-of select="@date"/></b><br/> 
        <xsl:apply-templates select="meal"/> 
      </li><br/>
    </xsl:template>
    
    <xsl:template match="meal">      
        <b> <xsl:value-of select="@number"/>. </b> <xsl:value-of select="weight/text()"/><xsl:text> </xsl:text>
        <xsl:value-of select="name/text()"/> <xsl:text> </xsl:text>
<xsl:value-of select="price/text()"/> Kč <br/>  
    </xsl:template> 

</xsl:stylesheet>
