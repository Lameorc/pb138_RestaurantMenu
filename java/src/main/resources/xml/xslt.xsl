<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <HTML>
          <HEAD><title>Jídelní lístek</title></HEAD>
          <BODY>
              <xsl:call-template select="day"/>
          </BODY>
        </HTML>
    </xsl:template>
    <xsl:template match="day">
      <xsl:for-each select="menus/day">
        <b><xsl:value-of select="./@name"/> <xsl:value-of select="./@date"/></b><br/>
        <xsl:apply-templates select="meal"/> 
       </xsl:for-each>    
    </xsl:template>
    
    <xsl:template match="meal">
      <xsl:for-each select="menus/day/meals/meal">
        <xsl:value-of select="./meals/meal/@number"/> <xsl:value-of select="./meals/meal/weight/text()"/>
        <xsl:value-of select="./meals/meal/name/text()"/> <xsl:value-of select="./meals/meal/price/text()"/><br/>
      </xsl:for-each>
    </xsl:template> 

</xsl:stylesheet>
  
    
  

