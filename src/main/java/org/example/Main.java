package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;//builder.parse

// java DOM parser:xml dosyasından verileri okuyacak yerleşik java kütüphanesi
//xml dosyasını aktarırken kullanılan üç ana sınıf:DocumentBuilderFactory,DocumentBuilder,Document
//xml dosyasını bir belge olarak iletmek için kullanacağımız şeyler
public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        // Yeni bir nesnesi oluşturduk
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Belge oluşturucu nesnesini döndürür
        DocumentBuilder builder = factory.newDocumentBuilder();

        //DocumentBuilderFactory ve DocumentBuilder soyut sınıfları xml dosyasını okumak için
        //xml dosyasına doğrudan iletebiliriz,veya xml dosya konumunu belirleriz.
        //parse metotları ile dosya kaynağı belirtilir xml dosyası belleğe yüklenir.
        Document document = builder.parse(".\\src\\NumberPlan.xml");

        //DOM XML OKUMA
        // extract root node form xml doc
        //kök düğüm olan nesnenin öğe türünü döndürür
        //kök ögeyi yakalarız ve bu kök ögeyi kullanarak tüm alt düğümleri yakalamamız gerekir
        Element root = document.getDocumentElement();
        // Tüm Company etiketlerini alın
        NodeList companyList = root.getElementsByTagName("Company");

        //buraya kadar tüm company alt düğümleri kök ögeden çıkarttık şimdi her bir alt ögeyi okumamız gerekir.
        //ve sonra her alt düğümden tüm etiketleri ve içerikleri çıkarmak gerekir.
        //nodeList ile kaç tane company düğümleri olduğunu yakaladık ama kaç tane company düğüm olduğunu getLength ile saydık.

        // Her bir Company etiketi üzerinde döngü yapın

        for (int i = 0; i < companyList.getLength(); i++) {
            //sıfıra değer veriyorum ilk düğümü aldık ve bu belirli düğümden her bir etiketi çıkarmam gerekiyor
            Node companyNode = companyList.item(i);
            //eğer belirli company düğüm nokta düğüm türünü alıyorsa etiketini alıcağız verileri çıkartacğız
            if (companyNode.getNodeType() == Node.ELEMENT_NODE) {
                //eğer düğüm belirli company düğüme özellikleri aynıysa gibi bişi o zaman alcağımız elementtir diyoruz
                Element companyElement = (Element) companyNode;
                //koşulu sağlıyorsa etiketlerine bakarız artık sağlamıyorsa aradığımız düğüm değildir bakmayacağız

                //öncelikle etiketleri adlandırdık şimdi bu etiket adını kullanarak verileri çıkartacağız
                //etiket adını çıkardığımız ögeden metin içeriği ögesi çıkartacağız metin içeriği alınır.
                //tamamını yazdıddır
                //dize dize metin içeriğini aldık
                String companyName = companyElement.getElementsByTagName("CompanyName").item(0).getTextContent();
                String route = companyElement.getElementsByTagName("Route").item(0).getTextContent();

                System.out.println("CompanyName: " + companyName);
                System.out.println("Route: " + route);

                // Her bir Company etiketi içindeki bir alt düğümdeki tüm Block etiketlerini alacağız
                NodeList blockList = companyElement.getElementsByTagName("Block");
                for (int j = 0; j < blockList.getLength(); j++) {
                    Node blockNode = blockList.item(j);
                    //yine aynı şekilde burda blockNode belirlediğimiz türdeyse elementnode dur diyoruz ve if'e giriyoruz
                    if (blockNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element blockElement = (Element) blockNode;
                        String ndc = blockElement.getElementsByTagName("NDC").item(0).getTextContent();
                        String prefix = blockElement.getElementsByTagName("Prefix").item(0).getTextContent();
                        String size = blockElement.getElementsByTagName("Size").item(0).getTextContent();

                        System.out.println("  Block - NDC: " + ndc + ", Prefix: " + prefix + ", Size: " + size);
                    }
                }
            }
        }
    }
}
