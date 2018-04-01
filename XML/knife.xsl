<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
    <html>
        <body>
            <h2>My CD Collection</h2>
            <table border="1">
            <tr bgcolor="#9acd32">
                <th>Type</th>
                <th>Handy</th>
                <th>Origin</th>
                <th>Length</th>
                <th>Width</th>
                <th>Blade</th>
                <th>Handles</th>
                <th>Blood Flow</th>
                <th>Value</th>
            </tr>
                <xsl:for-each select="type/type">
                    <xsl:sort select="origin"/>
            <tr>
                <td><xsl:value-of select="handy"/></td>
                <td><xsl:value-of select="origin"/></td>
                <td><xsl:value-of select="visual/length"/></td>
                <td><xsl:value-of select="visual/width"/></td>
                <td><xsl:value-of select="visual/material/blade"/></td>
                <td><xsl:value-of select="visual/material/handles"/></td>
                <td><xsl:value-of select="visual/material/blood-flow"/></td>
                <td><xsl:value-of select="value"/></td>
            </tr>
                </xsl:for-each>
            </table>
        </body>
    </html>
</xsl:template></xsl:stylesheet>