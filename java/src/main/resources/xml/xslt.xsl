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
          <HEAD><style>th, td {border-bottom: 1px solid black }
                       .meal {font-size: 20px}        
                 </style></HEAD>
          <BODY>
            <h1> Denní menu <xsl:value-of select="//day[@name= 'pondělí']/@date"/> - 
            <xsl:value-of select="//day[@name= 'pátek']/@date"/></h1>
            <table>  
                <xsl:apply-templates select="day"/>  
            </table>
          </BODY>
        </HTML>
    </xsl:template>

    <xsl:template match="day"> 
      <tr><th>
        <h2><xsl:value-of select="@name"/> - <xsl:value-of select="@date"/></h2></th></tr>
       
        <xsl:apply-templates select="meal"/> 

      <br/>
    </xsl:template>
    
    <xsl:template match="meal"> 
     <tr>
       <td class="meal"> 
        <b> <xsl:value-of select="@number"/>. </b> <xsl:value-of select="weight/text()"/><xsl:text> </xsl:text>
        <xsl:value-of select="name/text()"/> <xsl:text> </xsl:text>
        <xsl:value-of select="price/text()"/> Kč 
        </td> 
     </tr>
    </xsl:template> 

</xsl:stylesheet>
