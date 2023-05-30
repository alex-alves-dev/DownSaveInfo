package com.example.downsaveinfo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

public class AnalizadorXml {
    private String xmlDados;
    private String conteudo;

    public AnalizadorXml(String xmlDados) {
        this.xmlDados = xmlDados;
    }

    public String getXmlDados() {
        return xmlDados;}

    public String getConteudo() {
        return conteudo;
    }

        public void process() {

            boolean campoValido = false;
            boolean busca = true;


            try {


                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();


                factory.setNamespaceAware(true);


                XmlPullParser xpp = factory.newPullParser();


                xpp.setInput(new StringReader(xmlDados));


                int tipoDeEvento;



                while (busca) {


                    tipoDeEvento = xpp.next();


                    String tagName = xpp.getName();


                    switch (tipoDeEvento) {


                        case XmlPullParser.START_TAG:


                            if (tagName.equalsIgnoreCase("body")) {

                                campoValido = true;
                            }
                            break;


                        case XmlPullParser.TEXT:

                            if(campoValido)
                            {

                                conteudo=xpp.getText();

                                busca=false;
                            }
                            break;

                    }
                }
            }

            catch (Exception e) {

                e.printStackTrace();
            }

        }


    }