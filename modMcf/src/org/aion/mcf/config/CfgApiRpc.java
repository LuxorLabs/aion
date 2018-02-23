/*******************************************************************************
 * Copyright (c) 2017-2018 Aion foundation.
 *
 *     This file is part of the aion network project.
 *
 *     The aion network project is free software: you can redistribute it
 *     and/or modify it under the terms of the GNU General Public License
 *     as published by the Free Software Foundation, either version 3 of
 *     the License, or any later version.
 *
 *     The aion network project is distributed in the hope that it will
 *     be useful, but WITHOUT ANY WARRANTY; without even the implied
 *     warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *     See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with the aion network project source files.
 *     If not, see <https://www.gnu.org/licenses/>.
 *
 * Contributors:
 *     Aion foundation.
 *
 ******************************************************************************/
package org.aion.mcf.config;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author chris
 */
public final class CfgApiRpc {

    CfgApiRpc() {
        this.active = true;
        this.ip = "127.0.0.1";
        this.port = 8545;
    }

    private boolean active;

    private String ip;

    private int port;

    public void fromXML(final XMLStreamReader sr) throws XMLStreamException {
        this.active = Boolean.parseBoolean(sr.getAttributeValue(null, "active"));
        this.ip = sr.getAttributeValue(null, "ip");
        this.port = Integer.parseInt(sr.getAttributeValue(null, "port"));
        sr.next();
    }

    String toXML() {
        final XMLOutputFactory output = XMLOutputFactory.newInstance();
        output.setProperty("escapeCharacters", false);
        XMLStreamWriter xmlWriter;
        String xml;
        try {
            // <rpc active="false" ip="127.0.0.1" port="8545"/>

            Writer strWriter = new StringWriter();
            xmlWriter = output.createXMLStreamWriter(strWriter);
            xmlWriter.writeCharacters("\r\n\t\t");
            xmlWriter.writeStartElement("rpc");

            xmlWriter.writeAttribute("active", this.active ? "true" : "false");
            xmlWriter.writeAttribute("ip", this.ip);
            xmlWriter.writeAttribute("port", this.port + "");

            xmlWriter.writeEndElement();
            xml = strWriter.toString();
            strWriter.flush();
            strWriter.close();
            xmlWriter.flush();
            xmlWriter.close();
            return xml;
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean getActive() {
        return this.active;
    }

    public String getIp() {
        return this.ip;
    }

    public int getPort() {
        return this.port;
    }

}